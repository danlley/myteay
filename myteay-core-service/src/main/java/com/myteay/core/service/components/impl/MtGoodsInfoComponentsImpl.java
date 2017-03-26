/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.components.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.exceptions.MtBizException;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage;
import com.myteay.core.model.dinner.MtGoodsModel;
import com.myteay.core.model.dinner.repository.MtGoodsInfoRepository;
import com.myteay.core.model.user.convt.MtGoodsModelConvertor;
import com.myteay.core.service.components.MtGoodsInfoComponents;

/**
 * ��Ʒ��Ϣ�������
 * 
 * @author Administrator
 * @version $Id: MtGoodsInfoComponentsImpl.java, v 0.1 2016��3��5�� ����12:55:24 Administrator Exp $
 */
public class MtGoodsInfoComponentsImpl implements MtGoodsInfoComponents {

    /** ��־ */
    public static final Logger    logger = Logger.getLogger(MtGoodsInfoComponentsImpl.class);

    /** ��Ʒ��Ϣ�ִ��� */
    @Autowired
    private MtGoodsInfoRepository mtGoodsInfoRepository;

    /** 
     * @see com.myteay.core.service.components.MtGoodsInfoComponents#removeGoodsInfoById(java.lang.String)
     */
    @Override
    public MtOperateResult<String> removeGoodsInfoById(String id) {
        return mtGoodsInfoRepository.removeGoodsInfoById(id);
    }

    /** 
     * @see com.myteay.core.service.components.MtGoodsInfoComponents#findGoodsInfoById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtGoodsInfoMessage> findGoodsInfoById(String id) {

        MtOperateResult<MtGoodsInfoMessage> result = new MtOperateResult<MtGoodsInfoMessage>();
        MtOperateResult<MtGoodsModel> resultInner = null;
        try {
            resultInner = mtGoodsInfoRepository.findGoodsInfoById(id);
        } catch (Exception e) {
            logger.warn("��ѯ��Ʒ��Ϣ�����쳣 id=" + id, e);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        if (resultInner == null
            || resultInner.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS
            || resultInner.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯ��Ʒ��Ϣʧ��result=" + resultInner);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        MtGoodsInfoMessage message = MtGoodsModelConvertor.convertModel2Message(resultInner
            .getResult());
        if (message == null) {
            logger.warn("������Ʒ��Ϣ��������ʧ�� resultInner=" + resultInner);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        result.setOperateExResult(MtOperateExResultEnum.CAMP_OPERATE_SUCCESS);
        result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_SUCCESS);
        result.setResult(message);
        return result;
    }

    /** 
     * @see com.myteay.core.service.components.MtGoodsInfoComponents#saveGoodsInfo(com.myteay.common.service.facade.model.dinner.MtGoodsInfoMessage)
     */
    @Override
    public MtOperateResult<String> saveGoodsInfo(MtGoodsInfoMessage message) {
        MtGoodsModel model = MtGoodsModelConvertor.convertMessage2Model(message);

        MtOperateResult<String> result = new MtOperateResult<String>();
        if (model == null) {
            logger.warn("�޷�������Ʒ��Ʒ��Ϣ��model is null, message=" + message);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        return mtGoodsInfoRepository.saveGoodsInfo(model);
    }

    /** 
     * @see com.myteay.core.service.components.MtGoodsInfoComponents#queryGoodsList()
     */
    @Override
    public MtOperateResult<List<MtGoodsModel>> queryGoodsList() throws MtBizException {
        return mtGoodsInfoRepository.queryGoodsList();
    }

    /**
     * Setter method for property <tt>mtGoodsInfoRepository</tt>.
     * 
     * @param mtGoodsInfoRepository value to be assigned to property mtGoodsInfoRepository
     */
    public void setMtGoodsInfoRepository(MtGoodsInfoRepository mtGoodsInfoRepository) {
        this.mtGoodsInfoRepository = mtGoodsInfoRepository;
    }

}
