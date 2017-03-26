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
import com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage;
import com.myteay.core.model.dinner.MtGoodsPkgModel;
import com.myteay.core.model.dinner.repository.MtGoodsPkgInfoRepository;
import com.myteay.core.model.user.convt.MtGoodsPkgModelConvertor;
import com.myteay.core.service.components.MtGoodsPkgInfoComponents;

/**
 * �ײ���Ϣ�������
 * 
 * @author Administrator
 * @version $Id: MtGoodsPkgInfoComponentsImpl.java, v 0.1 2016��3��5�� ����9:42:34 Administrator Exp $
 */
public class MtGoodsPkgInfoComponentsImpl implements MtGoodsPkgInfoComponents {

    /** ��־ */
    public static final Logger       logger = Logger.getLogger(MtGoodsPkgInfoComponentsImpl.class);

    /** �ײ���Ϣ��Ʒ����ִ��� */
    @Autowired
    private MtGoodsPkgInfoRepository mtGoodsPkgInfoRepository;

    /** 
     * @see com.myteay.core.service.components.MtGoodsPkgInfoComponents#deleteGoodsPkgById(java.lang.String)
     */
    @Override
    public MtOperateResult<String> deleteGoodsPkgById(String id) {
        return mtGoodsPkgInfoRepository.deleteGoodsPkgById(id);
    }

    /** 
     * @see com.myteay.core.service.components.MtGoodsPkgInfoComponents#findGoodsPkgById(java.lang.String)
     */
    @Override
    public MtOperateResult<MtGoodsPkgInfoMessage> findGoodsPkgById(String id) {

        MtOperateResult<MtGoodsPkgInfoMessage> result = new MtOperateResult<MtGoodsPkgInfoMessage>();

        MtOperateResult<MtGoodsPkgModel> resultInner = mtGoodsPkgInfoRepository
            .findGoodsInfoById(id);
        if (resultInner == null
            || resultInner.getOperateExResult() != MtOperateExResultEnum.CAMP_OPERATE_SUCCESS
            || resultInner.getOperateResult() != MtOperateResultEnum.CAMP_OPERATE_SUCCESS) {
            logger.warn("��ѯָ���ײ���Ϣ��Ʒʧ�� id=" + id + " resultInner=" + resultInner);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        MtGoodsPkgInfoMessage message = MtGoodsPkgModelConvertor.convertModel2Message(resultInner
            .getResult());
        if (message == null) {
            logger.warn("��ѯָ���ײ���Ϣj��������ʧ�� id=" + id + " resultInner=" + resultInner);
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
     * @see com.myteay.core.service.components.MtGoodsPkgInfoComponents#saveGoodsPkgMessage(com.myteay.common.service.facade.model.dinner.MtGoodsPkgInfoMessage)
     */
    @Override
    public MtOperateResult<String> saveGoodsPkgMessage(MtGoodsPkgInfoMessage message) {

        MtGoodsPkgModel model = MtGoodsPkgModelConvertor.convertMessage2Model(message);
        MtOperateResult<String> result = new MtOperateResult<String>();
        if (model == null) {
            logger.warn("�ײ�����ģ�Ͳ����ã��޷������ײ�����ģ�͡�model is null, message=" + message);
            result.setOperateExResult(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
            result.setOperateResult(MtOperateResultEnum.CAMP_OPERATE_FAILED);
            return result;
        }

        return mtGoodsPkgInfoRepository.saveGoodsPkgModel(model);
    }

    /** 
     * @see com.myteay.core.service.components.MtGoodsPkgInfoComponents#queryGoodsPkgList()
     */
    @Override
    public MtOperateResult<List<MtGoodsPkgModel>> queryGoodsPkgList() throws MtBizException {
        return mtGoodsPkgInfoRepository.queryGoodsPkgList();
    }

}
