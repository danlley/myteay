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
 * 操作执行模板
 * 
 * @author Administrator
 * @version $Id: MtOperateManageTemplateImpl.java, v 0.1 2016年9月7日 下午8:42:24 Administrator Exp $
 */
public class MtOperateManageTemplateImpl implements MtOperateManageTemplate {

    /** 日志 */
    public static final Logger  logger = Logger.getLogger(MtOperateManageTemplateImpl.class);

    /** 会员数据库事务模板 */
    private TransactionTemplate myteayCustomerTransactionTemplate;

    /** 
     * @see com.myteay.core.service.manage.template.MtOperateManageTemplate#execute(com.myteay.common.service.facade.enums.MtProcessManageTypeEnum, java.lang.Object, com.myteay.core.service.manage.template.MtManageCallback)
     */
    @Override
    public MtOperateResult<String> execute(final MtProcessManageTypeEnum manageType,
                                           final Object obj, final MtManageCallback callback) {

        //基础业务处理
        if (manageType == null || obj == null || callback == null) {
            logger.warn("[业务处理模板]处理业务前的常规参数检查过程失败 manageType=" + manageType + " obj=" + obj
                        + " callback=" + callback);
            return new MtOperateResult<String>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                obj.toString(), "[业务处理模板]处理业务前的常规参数检查过程失败 manageType=" + manageType + " obj=" + obj
                                + " callback=" + callback,
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        return myteayCustomerTransactionTemplate
            .execute(new TransactionCallback<MtOperateResult<String>>() {
                /** 
                 * @see org.springframework.transaction.support.TransactionCallback#doInTransaction(org.springframework.transaction.TransactionStatus)
                 */
                public MtOperateResult<String> doInTransaction(TransactionStatus status) {
                    //变量初始化
                    MtOperateResult<String> returnValue = null;
                    String errorDetail = null;
                    String returnObject = null;
                    MtOperateResultEnum operateResult = null;
                    MtOperateExResultEnum operateExResult = null;
                    //业务处理主逻辑
                    try {
                        //step 1: 校验未通过时通过PromoCoreConfigException异常结束执行后续动作
                        MtProcessManageTypeEnum manageTypeInner = null;

                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]开始进入数据合法性验证阶段manageType=" + manageType + " obj="
                                        + obj);
                        }
                        manageTypeInner = callback.validate();

                        //step 2: 流程前业务处理
                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]开始进入业务处理前置业务阶段manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_BEFORE_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.beforeProcess();
                        }

                        //step 3: 流程辅助业务处理
                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]开始进入业务处理辅助流程阶段manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.auxiliaryProcess();
                        }

                        //step 4: 流程主业务处理
                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]开始进入业务处理主流程阶段manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_MAIN_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.process();
                        }

                        //step 5: 流程结束前业务处理
                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]开始进入流程结束前业务阶段manageType=" + manageType + " obj="
                                        + obj);
                        }
                        validateMtProcessManageType(manageType, obj);
                        if (MtProcessManageTypeEnum.CS_AFTER_PROCESS == manageTypeInner) {
                            manageTypeInner = callback.process();
                        }

                        //step 6: 流程执行结束前的扫尾工作
                        if (logger.isInfoEnabled()) {
                            logger.info("[业务处理模板]当前业务处理请求成功执行，manageType=" + manageType + " obj="
                                        + obj);
                        }
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
                        operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
                    }
                    //系统自定义异常处理
                    catch (MtBizProcessException e) {
                        status.setRollbackOnly();
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
                        operateExResult = MtOperateExResultEnum.getByValue(e.getOperateExResult());
                        errorDetail = "业务处理异常（PromoCoreConfigException）：" + e.getMessage();
                        logger.warn(errorDetail, e);
                    }
                    //未知异常处理
                    catch (Exception e) {
                        status.setRollbackOnly();
                        errorDetail = "业务处理异常（Exception）：" + e.getMessage();
                        operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
                        operateExResult = MtOperateExResultEnum.CAMP_PROCESS_UNKNOW_ERR;
                        logger.warn(errorDetail, e);
                    }
                    //最后构造返回值
                    finally {
                        returnValue = new MtOperateResult<String>(operateResult, returnObject,
                            errorDetail, operateExResult);
                    }

                    return returnValue;
                }

            });
    }

    /**
     * 执行真正的业务处理逻辑
     * 
     * @param manageType                操作类型
     * @param obj                       操作实体
     * @throws MtBizProcessException    业务处理异常
     */
    private void validateMtProcessManageType(MtProcessManageTypeEnum manageType, Object obj)
                                                                                             throws MtBizProcessException {
        if (manageType == null) {
            logger.warn("业务校验处理异常 obj=" + obj);

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
        //基础业务处理
        if (manageType == null || obj == null || callback == null) {
            logger.warn("[业务处理模板]处理业务前的常规参数检查过程失败 manageType=" + manageType + " obj=" + obj
                        + " callback=" + callback);
            return new MtOperateResult<String>(MtOperateResultEnum.CAMP_OPERATE_FAILED,
                obj.toString(), "[业务处理模板]处理业务前的常规参数检查过程失败 manageType=" + manageType + " obj=" + obj
                                + " callback=" + callback,
                MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        //变量初始化
        MtOperateResult<String> returnValue = null;
        String errorDetail = null;
        String returnObject = null;
        MtOperateResultEnum operateResult = null;
        MtOperateExResultEnum operateExResult = null;
        //业务处理主逻辑
        try {
            //step 1: 校验未通过时通过PromoCoreConfigException异常结束执行后续动作
            MtProcessManageTypeEnum manageTypeInner = null;

            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]开始进入数据合法性验证阶段manageType=" + manageType + " obj=" + obj);
            }
            manageTypeInner = callback.validate();

            //step 2: 流程前业务处理
            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]开始进入业务处理前置业务阶段manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_BEFORE_PROCESS == manageTypeInner) {
                manageTypeInner = callback.beforeProcess();
            }

            //step 3: 流程辅助业务处理
            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]开始进入业务处理辅助流程阶段manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_AUXILIARY_PROCESS == manageTypeInner) {
                manageTypeInner = callback.auxiliaryProcess();
            }

            //step 4: 流程主业务处理
            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]开始进入业务处理主流程阶段manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_MAIN_PROCESS == manageTypeInner) {
                manageTypeInner = callback.process();
            }

            //step 5: 流程结束前业务处理
            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]开始进入流程结束前业务阶段manageType=" + manageType + " obj=" + obj);
            }
            validateMtProcessManageType(manageType, obj);
            if (MtProcessManageTypeEnum.CS_AFTER_PROCESS == manageTypeInner) {
                manageTypeInner = callback.process();
            }

            //step 6: 流程执行结束前的扫尾工作
            if (logger.isInfoEnabled()) {
                logger.info("[业务处理模板]当前业务处理请求成功执行，manageType=" + manageType + " obj=" + obj);
            }
            operateResult = MtOperateResultEnum.CAMP_OPERATE_SUCCESS;
            operateExResult = MtOperateExResultEnum.CAMP_OPERATE_SUCCESS;
        }
        //系统自定义异常处理
        catch (MtBizProcessException e) {
            operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
            operateExResult = MtOperateExResultEnum.getByValue(e.getOperateExResult());
            errorDetail = "业务处理异常（PromoCoreConfigException）：" + e.getMessage();
            logger.warn(errorDetail, e);
        }
        //未知异常处理
        catch (Exception e) {
            errorDetail = "业务处理异常（Exception）：" + e.getMessage();
            operateResult = MtOperateResultEnum.CAMP_OPERATE_FAILED;
            operateExResult = MtOperateExResultEnum.CAMP_PROCESS_UNKNOW_ERR;
            logger.warn(errorDetail, e);
        }
        //最后构造返回值
        finally {
            returnValue = new MtOperateResult<String>(operateResult, returnObject, errorDetail,
                operateExResult);
        }

        return returnValue;
    }

    /**
     * Setter method for property <tt>myteayCustomerTransactionTemplate</tt>.
     * 
     * @param myteayCustomerTransactionTemplate value to be assigned to property myteayCustomerTransactionTemplate
     */
    public void setMyteayCustomerTransactionTemplate(TransactionTemplate myteayCustomerTransactionTemplate) {
        this.myteayCustomerTransactionTemplate = myteayCustomerTransactionTemplate;
    }
}
