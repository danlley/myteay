/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.core.service.manage.template;

import com.myteay.common.service.facade.enums.MtProcessManageTypeEnum;
import com.myteay.common.service.facade.model.MtOperateResult;

/**
 * ����ִ��ģ��<br><br>
 * 1��������ģ�壺
 * <ul>
 *      <li>Ϊȷ������һ���ԣ�������ģ��Ӧ��������ҵ������������н���</li>
 * </ul>
 * 
 * 2����������Ĵ���ģ��ʹ�ó�����
 * <ul>
 *      <li>��������Ѿ����ϲ���ȫ���ƣ�������������Ȼ����ǰ�����С��º���Ҫ���ݲ�������������</li>
 * </ul>
 * 
 * @author Administrator
 * @version $Id: MtOperateManageTemplate.java, v 0.1 2016��9��7�� ����8:06:31 Administrator Exp $
 */
public interface MtOperateManageTemplate {

    /**
     * ����ִ��ģ��
     * 
     * @param manageType        ��������
     * @param obj               ��������
     * @param callback          �����ص�������ʵ��
     * @return                  �������
     */
    public MtOperateResult<String> execute(MtProcessManageTypeEnum manageType, Object obj,
                                           MtManageCallback callback);

    /**
     * ����������Ĳ���ģ��
     * 
     * @param manageType        ��������
     * @param obj               ��������
     * @param callback          �����ص�������ʵ��
     * @return                  ִ�н��
     */
    public MtOperateResult<String> executeWithOutTrans(final MtProcessManageTypeEnum manageType,
                                                       final Object obj,
                                                       final MtManageCallback callback);
}
