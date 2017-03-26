/*
 * Copyright (c) 2000-2004 All Rights Reserved.
 */
package com.myteay.common.util.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ��������ҳ����Ϣ��<code>List</code>��
 *
 * @author Michael Zhou
 * @version $Id: PageList.java,v 1.1 2005/11/08 14:01:56 calvin Exp $
 */
public class PageList<T> extends ArrayList<T> {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3257568390985103409L;
    private Paginator         paginator;

    /**
     * ����һ��<code>PageList</code>��
     */
    public PageList() {
        paginator = new Paginator();
    }

    /**
     * ����<code>PageList</code>������ָ��<code>Collection</code>�е����ݸ��Ƶ��µ�list�С�
     *
     * @param c Ҫ���Ƶ�<code>Collection</code>
     */
    public PageList(Collection<T> c) {
        this(c, null);
    }

    /**
     * ����<code>PageList</code>������ָ��<code>Collection</code>�е����ݸ��Ƶ��µ�list�С�
     *
     * @param c Ҫ���Ƶ�<code>Collection</code>
     */
    public PageList(Collection<T> c, Paginator paginator) {
        super(c);
        this.paginator = (paginator == null) ? new Paginator() : paginator;
    }

    /**
     * ȡ�÷�ҳ�����ɴ���ȡ���йط�ҳ��ҳ���������Ϣ��
     *
     * @return ��ҳ������
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * ���÷�ҳ����
     *
     * @param paginator Ҫ���õķ�ҳ������
     */
    public void setPaginator(Paginator paginator) {
        if (paginator != null) {
            this.paginator = paginator;
        }
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getPageSize() {
        return paginator.getItemsPerPage();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getTotalItem() {
        return paginator.getItems();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getTotalPage() {
        return paginator.getPages();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setPageSize(int i) {
        paginator.setItemsPerPage(i);
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setTotalItem(int i) {
        paginator.setItems(i);
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setTotalPage(int i) {
        setTotalItem(i * getPageSize());
    }
}
