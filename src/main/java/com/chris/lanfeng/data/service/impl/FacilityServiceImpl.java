package com.chris.lanfeng.data.service.impl;

import com.chris.lanfeng.data.common.*;
import com.chris.lanfeng.data.entity.Facility;
import com.chris.lanfeng.data.entity.FacilityRecord;
import com.chris.lanfeng.data.mapper.FacilityMapper;
import com.chris.lanfeng.data.mapper.FacilityRecordMapper;
import com.chris.lanfeng.data.service.FacilityService;
import com.chris.lanfeng.result.ErrorCode;
import com.chris.lanfeng.result.Result;
import com.chris.lanfeng.utils.*;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Instant;
import java.util.*;

import static com.chris.lanfeng.utils.JsonUtils.toJson;

/**
 * Created by xiaoxu on 2018/1/25.
 */
@Service
public class FacilityServiceImpl implements FacilityService {
    private final static Logger logger = LoggerFactory.getLogger(FacilityServiceImpl.class);

    private Map<Long, Long> map = Maps.newConcurrentMap();
    private static long updateCount = Constant.DEFAULT_ID;

    private static WebSocket ws = null;

    @Resource
    private FacilityMapper facilityMapper;
    @Resource
    private FacilityRecordMapper facilityRecordMapper;

    @Override
    public void update() {
        logger.info("update start");
        try {
            ws.getSocket().sendUrgentData(0xFF);
        } catch (Exception e) {
            System.out.println("连接失败，3秒后重新连接" + e.getMessage());
            getData();
            try {
                Thread.sleep(3000);
            } catch (Exception e1) {

            }
        }
        logger.info("update end");
    }

    @Override
    public void getData() {
        logger.info("get data start");
        try {
            getDeviceData();
            updateCount = Constant.DEFAULT_ID;
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("token", Constant.TOKEN);
            ws = new WebSocket(new URI("ws://" + Constant.URI + ":" + Constant.PORT));
            ws.setHeaders(headers);
            ws.connect();
            String request = "{type:0,content:{token:\"" + Constant.TOKEN + "\"}}";
            ws.send(request);
            Object requestObject = null;
            while (true) {
                try {
                    requestObject = getObject(ws.recv());
                    if (requestObject == null) {
                        continue;
                    }
                    updateCount++;
                    logger.info("time thread :{}", updateCount);
                    if (requestObject instanceof FacilityListRequest) {
                        FacilityListRequest listRequest = (FacilityListRequest) requestObject;
                        logger.info("listRequest:{}", JsonUtils.toString(listRequest));
                        addFacilityRecord(listRequest);
                    } else if (requestObject instanceof DeviceListRequest) {
                        DeviceListRequest listRequest = (DeviceListRequest) requestObject;
                        logger.info("DeviceListRequest:{}", JsonUtils.toString(listRequest));
                    } else {
                        logger.info("Request:{}", JsonUtils.toString(requestObject));
                    }
                } catch (Exception e) {
                    logger.info("data error");
                }
            }
        } catch (Exception e) {
            try {
                ws.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        logger.info("get data end ");
    }


    @Override
    public void getDeviceData() {
        try {
            map.clear();
            String json = HttpUtils.sendGet();
            DeviceDataRequest deviceDataRequest = toJson(json, DeviceDataRequest.class);
            logger.info("deviceDataRequest:{}", JsonUtils.toString(deviceDataRequest));
            for (DeviceData deviceData : deviceDataRequest.getData()) {
                map.put(deviceData.getId(), deviceData.getUniqueId());
            }
        } catch (Exception e) {
//           e.printStackTrace();
        }
    }

    @Override
    public void getDataTest(String json) {
        try {
            getDeviceData();
            Object requestObject = getObject(json);
            if (requestObject instanceof FacilityListRequest) {
                FacilityListRequest listRequest = (FacilityListRequest) requestObject;
                addFacilityRecord(listRequest);
            } else if (requestObject instanceof DeviceListRequest) {
                DeviceListRequest listRequest = (DeviceListRequest) requestObject;
            }
        } catch (Exception e) {
            logger.info("data error");
        }
    }

    private Object getObject(String json) {
        logger.info("json:{}", json);
        try {
            if (json.indexOf("devices") != -1) {
                DeviceListRequest requestObject = toJson(json, DeviceListRequest.class);
                return requestObject;
            } else if (json.indexOf("positions") != -1) {
                FacilityListRequest requestObject = toJson(json, FacilityListRequest.class);
                return requestObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Result<Void> addFacility(long deviceId, String plateNumber) {
        Result<Void> result = Result.newResult();
        if (deviceId == Constant.DEFAULT_ID || StringUtils.isBlank(plateNumber)) {
            result.addError(ErrorCode.ARGUMENTS_ERROR);
            return result;
        }
        Facility facility = facilityMapper.getByDeviceId(deviceId);
        if (facility != null) {
            result.addError(ErrorCode.DEVICE_EXIST);
            return result;
        }
        facility = facilityMapper.getByPlateNumber(plateNumber);
        if (facility != null) {
            result.addError(ErrorCode.PLATE_NUMBER_EXIST);
            return result;
        }
        facility = new Facility();
        facility.setDeviceId(deviceId);
        facility.setPlateNumber(plateNumber);
        facility.setCreateTime(Instant.now().toEpochMilli());
        int uRes = facilityMapper.insert(facility);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.SYSTEM_ERROR);
            return result;
        }
        return result;
    }

    @Override
    public Result<List<Facility>> findAll() {
        Result<List<Facility>> result = Result.newResult();
        List<Facility> list = facilityMapper.findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            result.setData(list);
        }
        return result;
    }

    @Override
    public Result<Void> deleteFacility(long id) {
        Result<Void> result = Result.newResult();
        int uRes = facilityMapper.delete(id);
        if (uRes == Constant.NUMBER_ZERO) {
            result.addError(ErrorCode.SYSTEM_ERROR);
            return result;
        }
        return result;
    }

    @Override
    public Result<FacilityRecord> getLastFacilityRecord(long deviceId) {
        Result<FacilityRecord> result = Result.newResult();
        FacilityRecord record = facilityRecordMapper.getLastFacilityRecordByDeviceId(deviceId);
        if (record != null) {
            result.setData(record);
        }
        return result;
    }

    @Override
    @Async("commonExecutor")
    public void addFacilityRecord(FacilityListRequest request) {
        List<FacilityData> datas = request.getPositions();
        if (CollectionUtils.isNotEmpty(datas)) {
            Facility facility = null;
            FacilityRecord record = null;
            long deviceId = Constant.DEFAULT_ID;
            for (FacilityData data : datas) {
                if (map.get(data.getDeviceId()) == null) {
                    getDeviceData();
                } else {
                    deviceId = map.get(data.getDeviceId());
                }
                facility = facilityMapper.getByDeviceId(deviceId);
                if (facility != null) {
                    record = BeanUtil.copyBean(data, FacilityRecord.class);
                    if (record != null) {
                        record.setUploadTime(data.getTime());
                        record.setDeviceId(deviceId);
                        facilityRecordMapper.insert(record);
                        scocket(getResponse(getLocation(facility, data)), (short) 97);
                        if (data.getSpeed() > 110) {
                            scocket(getResponse(getAlarmData(facility, data)), (short) 96);
                        }
                    }
                }
            }
        }
    }

    public static void scocket(LocationResponse response, short code) {
        Socket socket = null;
        try {
            socket = new Socket("210.12.209.156", 9004);
            String str = JsonUtils.toString(response);
            logger.info("scocket str:{} code:{}", str, code);
            OutputStream os = socket.getOutputStream();
//            String str = "{\"source\":\"03\",\"time\":\"传输时间\",\"sysId\":\"333\",\"data\":[{\"positionDes\":\"444\",\"alarmInfoNo\":\"111\",\"deptName\":\"所属单位\",\"alarmType\":\"报警类型\",\"startTime\":\"1449631478000\",\"endTime\":\"结束时间\",\"alarmSpeed\":\"3.700000047683716\",\"plateNumber\":\"车牌号\",\"alarmInfo\":\"报警附加信息\",\"latitude\":\"105.425597\",\"longitude\":\"30.161886\",\"address\":\"报警地址\",\"positioningTime\":\"" + (System.currentTimeMillis() + 300000) + "\"}]}";
            byte[] content = str.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(content.length + 8);
            buffer.order(ByteOrder.BIG_ENDIAN);
            buffer.putShort(code);
            buffer.putShort((short) 0);
            buffer.putInt(content.length);
            buffer.put(content);
            buffer.flip();
//            System.out.println(Arrays.toString(buffer.array()));
            os.write(buffer.array());
            os.flush();

            InputStream is = socket.getInputStream();
            byte[] bb = new byte[1024];
            int len;
            while ((len = is.read(bb)) != -1) {
                System.out.println(new String(bb, "UTF-8"));
            }

        } catch (Exception e) {
            logger.info("scocket response error");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                logger.info("scocket response error");
            }
        }
    }

    private LocationResponse getResponse(Object object) {
        LocationResponse response = new LocationResponse();
        response.setData(object);
        response.setTime(Instant.now().toEpochMilli());
        return response;
    }

    private List<FacilityLocation> getLocation(Facility facility, FacilityData data) {
        List<FacilityLocation> locations = new ArrayList<>();
        FacilityLocation location = new FacilityLocation();
        location.setPlateNumber(facility.getPlateNumber());
        location.setLatitude(data.getLatitude());
        location.setLongitude(data.getLongitude());
        location.setMileage(data.getCourse());
        location.setSpeed(data.getSpeed());
        location.setAddstatus(0);
        location.setPositioningTime(DateUtil.getDate(data.getTime()));
        locations.add(location);
        return locations;
    }

    private List<AlarmData> getAlarmData(Facility facility, FacilityData data) {
        List<AlarmData> alarmDatas = new ArrayList<>();
        AlarmData alarmData = new AlarmData();
        alarmData.setPlateNumber(facility.getPlateNumber());
        alarmData.setLatitude(data.getLatitude());
        alarmData.setLongitude(data.getLongitude());
        alarmData.setAlarmSpeed(data.getSpeed());
        alarmData.setAlarmType("超速");
        alarmData.setAlarmInfoNo(OrderUtils.generateGroupStatId());
        alarmData.setStartTime(new Date());
        alarmDatas.add(alarmData);
        return alarmDatas;
    }


}
