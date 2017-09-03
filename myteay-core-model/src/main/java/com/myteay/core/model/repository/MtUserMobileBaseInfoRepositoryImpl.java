/**
 * Myteay.com Inc.
 * Copyright (c) 2005-2017 All Rights Reserved.
 */
package com.myteay.core.model.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myteay.common.dal.daointerface.UserMobileBaseInfoDAO;
import com.myteay.common.dal.dataobject.UserMobileBaseInfoDO;
import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.core.model.user.MtUserMobileBaseInfoModel;
import com.myteay.core.model.user.enums.MtUserMobileBaseInfoTypeEnum;

/**
 * �û��ֻ�������Ϣ�����ִ�
 * 
 * @author danlley
 * @version $Id: MtUserMobileBaseInfoRepositoryImpl.java, v 0.1 Sep 3, 2017 5:31:01 PM danlley Exp $
 */
public class MtUserMobileBaseInfoRepositoryImpl implements MtUserMobileBaseInfoRepository {

    /** ��־ */
    public static final Logger    logger = Logger.getLogger(MtUserMobileBaseInfoRepositoryImpl.class);

    /** �û��ֻ�������Ϣ����DAO */
    private UserMobileBaseInfoDAO userMobileBaseInfoDAO;

    /** 
     * @see com.myteay.core.model.repository.MtUserMobileBaseInfoRepository#saveUserMobileInfo(com.myteay.core.model.user.MtUserMobileBaseInfoModel)
     */
    @Override
    public MtOperateResult<String> saveUserMobileInfo(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel) {

        MtOperateResult<String> result = new MtOperateResult<String>();

        UserMobileBaseInfoDO userMobileBaseInfoDO = convertModel2DO(mtUserMobileBaseInfoModel);
        if (userMobileBaseInfoDO == null) {
            logger.warn("�����ֻ�������Ϣ������ģ��ʧ�� mtUserMobileBaseInfoModel=" + mtUserMobileBaseInfoModel);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        UserMobileBaseInfoDO returnResult = userMobileBaseInfoDAO.queryUserMobileBaseInfo(userMobileBaseInfoDO);
        if (returnResult != null) {
            logger.warn("��ǰ�û��ֻ���Ϣ�Ѵ��ڣ�������б��� mtUserMobileBaseInfoModel=" + mtUserMobileBaseInfoModel);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_QRCODE_EXE_FAILED);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        String returnVal = userMobileBaseInfoDAO.insert(userMobileBaseInfoDO);

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(returnVal);

        return result;
    }

    /**
     * ���ֻ�������Ϣģ��ת��Ϊ����ģ��
     * 
     * @param mtUserMobileBaseInfoModel
     * @return
     */
    private UserMobileBaseInfoDO convertModel2DO(MtUserMobileBaseInfoModel mtUserMobileBaseInfoModel) {

        if (mtUserMobileBaseInfoModel == null) {
            logger.warn("[�û�ע��]mtUserMobileBaseInfoModel is null");
            return null;
        }

        UserMobileBaseInfoDO userMobileBaseInfoDO = new UserMobileBaseInfoDO();
        //userMobileBaseInfoDO.setGmt_mModified(gmt_mModified);
        //userMobileBaseInfoDO.setGmtCreated(gmtCreated);
        //userMobileBaseInfoDO.setId(id);
        userMobileBaseInfoDO.setDataInfoType(MtUserMobileBaseInfoTypeEnum.CS_USER_REG.getValue());
        userMobileBaseInfoDO.setUserid(mtUserMobileBaseInfoModel.getUserid());

        userMobileBaseInfoDO.setAvailMemory(getSingleJsonValue(mtUserMobileBaseInfoModel.getMobileAvailMemory()));
        userMobileBaseInfoDO.setMacAddr(getSingleJsonValue(mtUserMobileBaseInfoModel.getMobileMacAddress()));
        userMobileBaseInfoDO.setRootAble(getSingleJsonValue(mtUserMobileBaseInfoModel.getMobileRootProperty()));
        userMobileBaseInfoDO.setTotalMemory(getSingleJsonValue(mtUserMobileBaseInfoModel.getMobileTotalMemory()));

        Map<String, String> mobileBaseInfoMap = convertJson2Map(mtUserMobileBaseInfoModel.getMobileBaseInfo());
        userMobileBaseInfoDO.setImeiNo(mobileBaseInfoMap.get("IMEI"));
        userMobileBaseInfoDO.setIesiNo(mobileBaseInfoMap.get("IESI"));
        userMobileBaseInfoDO.setMobileType(mobileBaseInfoMap.get("mtype"));
        userMobileBaseInfoDO.setMobileBrand(mobileBaseInfoMap.get(""));
        userMobileBaseInfoDO.setMobileNo(mobileBaseInfoMap.get("number"));

        Map<String, String> mobileCPUInfoMap = convertJson2Map(mtUserMobileBaseInfoModel.getMobileCpuInfo());
        userMobileBaseInfoDO.setCpu(mobileCPUInfoMap.get("CPU"));
        userMobileBaseInfoDO.setCpuRange(mobileCPUInfoMap.get("CPU RANDGE"));

        Map<String, String> mobilePkgInfoMap = convertJson2Map(mtUserMobileBaseInfoModel.getMobilePackageInfo());
        userMobileBaseInfoDO.setApkPackage(mobilePkgInfoMap.get("Package"));
        userMobileBaseInfoDO.setVersionName(mobilePkgInfoMap.get("versionName"));
        userMobileBaseInfoDO.setVersionCode(mobilePkgInfoMap.get("versionCode"));

        Map<String, String> mobileSizeInfoMap = convertJson2Map(mtUserMobileBaseInfoModel.getMobileSize());
        userMobileBaseInfoDO.setMobileWidth(mobileSizeInfoMap.get("Width"));
        userMobileBaseInfoDO.setMobileHeight(mobileSizeInfoMap.get("Height"));

        return userMobileBaseInfoDO;
    }

    /**
     * ��ȡ����Json���е�ֵ��Ϣ
     * 
     * @param source
     * @return
     */
    private String getSingleJsonValue(String source) {
        return JSONObject.parseArray(source).getString(1);
    }

    /**
     * ��Json���е�key-value��ת��ΪMap��ʽ������ȡֵ
     * 
     * @param json
     * @return
     */
    private Map<String, String> convertJson2Map(String json) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONArray jsonArray = JSONObject.parseArray(json);
            for (Object obj : jsonArray) {
                map.put(((JSONArray) obj).getString(0), ((JSONArray) obj).getString(1));
            }
        } catch (Throwable e) {
            logger.warn("�ֻ�������Ϣ����Jsonת�����̳���json=" + json, e);
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        //String str = "[\"[\"IMEI\",\"358811077588406\"]\",\"[\"IESI\",\"460038410823066\"]\"]";
        String str = "[[\"��С��\",\"130 0298 3692\"],[\"�޶���\",\"130 2291 8302\"],[\"ţ����\",\"133 9933 5059\"]]";
        MtUserMobileBaseInfoRepositoryImpl inst = new MtUserMobileBaseInfoRepositoryImpl();
        Map<String, String> map = inst.convertJson2Map(str);
        System.out.println(map);
    }

    /**
     * Setter method for property <tt>userMobileBaseInfoDAO</tt>.
     * 
     * @param userMobileBaseInfoDAO value to be assigned to property userMobileBaseInfoDAO
     */
    public void setUserMobileBaseInfoDAO(UserMobileBaseInfoDAO userMobileBaseInfoDAO) {
        this.userMobileBaseInfoDAO = userMobileBaseInfoDAO;
    }
}
