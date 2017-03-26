/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.manage.template;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.myteay.common.service.facade.enums.MtOperateExResultEnum;
import com.myteay.common.service.facade.enums.MtOperateResultEnum;
import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.service.facade.model.MtOperateResult;
import com.myteay.common.utils.exception.MtBizProcessException;

/**
 * ����ִ��ģ��
 * 
 * @author Administrator
 * @version $Id: MtOperateManageTemplateImpl.java, v 0.1 2016��9��7�� ����8:42:24 Administrator Exp $
 */
public class MtOperateManageTemplateImpl implements MtOperateManageTemplate {

    /** ��־ */
    public static final Logger  logger = Logger.getLogger(MtOperateManageTemplateImpl.class);

    /** ��Ա���ݿ�����ģ�� */
    private TransactionTemplate myteayTransactionTemplate;

    /** 
     * @see com.myteay.core.service.manage.template.MtOperateManageTemplate#execute(com.myteay.common.service.facade.enums.MtProcessManageTypeEnum, java.lang.Object, com.myteay.core.service.manage.template.MtManageCallback)
     */
    @Override
    public MtOperateResult<String> execute(final MtProcessManageTypeEnum manageType,
                                           final Object obj, final MtManageCallback callback) {

        //����ҵ����
        if (manageType == null || obj == null || callback == null) {
            logger.warn("[ҵ����ģ��]����ҵ��ǰ�ĳ������������ʧ�� manageType=" + manageType + " obj=" + obj
                        + " callback=" + callback);
            return new MtOperateResult<String>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                obj.toString(), "[ҵ����ģ��]����ҵ��ǰ�ĳ������������ʧ�� manageType=" + manageType + " obj=" + obj
                                + " callback=" + callback,
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        return myteayTransactionTemplate
            .execute(new TransactionCallback<MtOperateResult<String>>() {
                /** 
                 * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
                 */
                public MtOperateResult<String> doInTransaction(TransactionStatus status) {
                    //������ʼ��
                    MtOperateResult<String> returnValue = null;
                    String errorDetail = null;
                    String returnObject = null;
                    MtOperateResultEnum operateResult = null;
                    MtOperateExResultEnum operateExResult = null;
                    //ҵ�������߼�
                    try {
                        //step 1: У��δͨ��ʱͨ��PromoCoreConfigException�쳣����ִ�к�������
                        MtProcessManageTypeEnum manageTypeInner = null;

                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ʼ�������ݺϷ�����֤�׶�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        manageTypeInner = callback.validate();

                        //step 2: ����ǰҵ����
                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ʼ����ҵ����ǰ��ҵ��׶�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_BEFORE_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.beforeProcess();
                        }

                        //step 3: ���̸���ҵ����
                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ʼ����ҵ���������̽׶�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.auxiliaryProcess();
                        }

                        //step 4: ������ҵ����
                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ʼ����ҵ���������̽׶�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_MAIN_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.process();
                        }

                        //step 5: ���̽���ǰҵ����
                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ʼ�������̽���ǰҵ��׶�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_AFTER_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.process();
                        }

                        //step 6: ����ִ�н���ǰ��ɨβ����
                        if (logger.isInfoEnabled()) {
                            logger.info("[ҵ����ģ��]��ǰҵ��������ɹ�ִ�У�manageType=" + manageType + " obj="
                                        + obj);
                        }
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
                        operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
                    }
                    //ϵͳ�Զ����쳣����
                    catch (MtBizProcessException e) {
                        status.setRollbackOnly();
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
                        operateExResult = MtOperateExResultEnum.getByValue(e.getOperateExResult());
                        errorDetail = "ҵ�����쳣��PromoCoreConfigException����" + e.getMessage();
                        logger.warn(errorDetail, e);
                    }
                    //δ֪�쳣����
                    catch (Exception e) {
                        status.setRollbackOnly();
                        errorDetail = "ҵ�����쳣��Exception����" + e.getMessage();
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
                        operateExResult = MtOperateExResultEnum.CAMP_PROCESS_UNKNOW_ERR;
                        logger.warn(errorDetail, e);
                    }
                    //����췵��ֵ
                    finally {
                        returnValue = new MtOperateResult<String>(operateResult, returnObject,
                            errorDetail, operateExResult);
                    }

                    return returnValue;
                }

            });
    }

    /**
     * ִ��������ҵ�����߼�
     * 
     * @param manageType                ��������
     * @param obj                       ����ʵ��
     * @throws MtBizProcessException    ҵ�����쳣
     */
    private void validateMtProcessManageType(MtProcessManageTypeEnum manageType, Object obj)
                                                                                            throws MtBizProcessException {
        if (manageType == null) {
            logger.warn("ҵ��У�鴦���쳣 obj=" + obj);

            throw new MtBizProcessException(MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getCode(),
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS.getMessage());
        }
    }

    /** 
     * @see com.myteay.core.service.manage.template.MtOperateManageTemplate#executeWithOutTrans(com.myteay.common.service.facade.enums.MtProcessManageTypeEnum, java.lang.Object, com.myteay.core.service.manage.template.MtManageCallback)
     */
    @Override
    public MtOperateResult<String> executeWithOutTrans(MtProcessManageTypeEnum manageType,
                                                       Object obj, MtManageCallback callback) {
        //����ҵ����
        if (manageType == null || obj == null || callback == null) {
            logger.warn("[ҵ����ģ��]����ҵ��ǰ�ĳ������������ʧ�� manageType=" + manageType + " obj=" + obj
                        + " callback=" + callback);
            return new MtOperateResult<String>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                obj.toString(), "[ҵ����ģ��]����ҵ��ǰ�ĳ������������ʧ�� manageType=" + manageType + " obj=" + obj
                                + " callback=" + callback,
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        //������ʼ��
        MtOperateResult<String> returnValue = null;
        String errorDetail = null;
        String returnObject = null;
        MtOperateResultEnum operateResult = null;
        MtOperateExResultEnum operateExResult = null;
        //ҵ�������߼�
        try {
            //step 1: У��δͨ��ʱͨ��PromoCoreConfigException�쳣����ִ�к�������
            MtProcessManageTypeEnum manageTypeInner = null;

            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ʼ�������ݺϷ�����֤�׶�manageType=" + manageType + " obj=" + obj);
            }
            manageTypeInner = callback.validate();

            //step 2: ����ǰҵ����
            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ʼ����ҵ����ǰ��ҵ��׶�manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_BEFORE_PROCESS == manageTypeInner) {
                manageTypeInner = callback.beforeProcess();
            }

            //step 3: ���̸���ҵ����
            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ʼ����ҵ���������̽׶�manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS == manageTypeInner) {
                manageTypeInner = callback.auxiliaryProcess();
            }

            //step 4: ������ҵ����
            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ʼ����ҵ���������̽׶�manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_MAIN_PROCESS == manageTypeInner) {
                manageTypeInner = callback.process();
            }

            //step 5: ���̽���ǰҵ����
            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ʼ�������̽���ǰҵ��׶�manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_AFTER_PROCESS == manageTypeInner) {
                manageTypeInner = callback.process();
            }

            //step 6: ����ִ�н���ǰ��ɨβ����
            if (logger.isInfoEnabled()) {
                logger.info("[ҵ����ģ��]��ǰҵ��������ɹ�ִ�У�manageType=" + manageType + " obj=" + obj);
            }
            operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
            operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
        }
        //ϵͳ�Զ����쳣����
        catch (MtBizProcessException e) {
            operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
            operateExResult = MtOperateExResultEnum.getByValue(e.getOperateExResult());
            errorDetail = "ҵ�����쳣��PromoCoreConfigException����" + e.getMessage();
            logger.warn(errorDetail, e);
        }
        //δ֪�쳣����
        catch (Exception e) {
            errorDetail = "ҵ�����쳣��Exception����" + e.getMessage();
            operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
            operateExResult = MtOperateExResultEnum.CAMP_PROCESS_UNKNOW_ERR;
            logger.warn(errorDetail, e);
        }
        //����췵��ֵ
        finally {
            returnValue = new MtOperateResult<String>(operateResult, returnObject, errorDetail,
                operateExResult);
        }

        return returnValue;
    }

    /**
     * Setter method for property <tt>myteayTransactionTemplate</tt>.
     * 
     * @param myteayTransactionTemplate value to be assigned to property myteayTransactionTemplate
     */
    public void setMyteayTransactionTemplate(TransactionTemplate myteayTransactionTemplate) {
        this.myteayTransactionTemplate = myteayTransactionTemplate;
    }
}
