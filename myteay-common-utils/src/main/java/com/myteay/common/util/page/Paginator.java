package com.myteay.common.util.page;

import java.io.Serializable;

/**
 * ��ҳ����
 * 
 * <p>
 * ���������webҳ���Ϸ�ҳ��ʾ�������ݣ�����ҳ��͵�ǰҳ��ƫ������ʮ�ַ���ʵ�á�
 * </p>
 * 
 * <p>
 * ����ֻ��Ҫ֪���ܹ��ж������ǰ��ʾÿ��ҳ��ÿҳ��ʾ����Ϳ��԰��������������ݣ����ұ�֤���м��㶼�ó������ֵ�����õ���ҳ�볬���߽�֮������⡣
 * </p>
 * 
 * <p>
 * ʹ�÷�������:
 * <pre><![CDATA[
 * 
 *   // ����һ����ҳ���������ڴ�ָ��ÿҳ��ʾ���Ҳ�����Ժ���ָ����
 *   // ���û��ָ������ʹ��Ĭ��ֵÿҳ�����ʾ10�
 *   Paginator pg = new Paginator();        // �� new Paginator(itemsPerPage);
 * 
 *   // �������ܹ��м�������������С��0���Ϳ���0��
 *   pg.setItems(123);
 * 
 *   // �����֪���м������������
 *   pg.setItems(Paginator.UNKNOWN_ITEMS);
 * 
 *   // ����Ĭ�ϵ�ǰҳ�ǵ�һҳ��������Ըı�����
 *   pg.setPage(3);                         // ������ǰҳ����3�ˣ����õ���ҳ���ᳬ����ҳ����
 * 
 *   // ��������Եõ����������ˡ�
 *   int currentPage = pg.getPage();        // �õ���ǰҳ
 *   int totalPages  = pg.getPages();       // �ܹ��м�ҳ
 *   int totalItems  = pg.getItems();       // �ܹ��м���
 *   int beginIndex  = pg.getBeginIndex();  // �õ���ǰҳ��һ������(��1��ʼ����)
 *   int endIndex    = pg.getEndIndex();    // �õ���ǰҳ���һ������(Ҳ�Ǵ�1��ʼ��)
 *   int offset      = pg.getOffset();      // offset��length������Ϊmysql��ѯ���
 *   int length      = pg.getLength();      //     ��limit offset��length�Ӿ䡣
 * 
 *   // ��������������
 *   setItemsPerPage(20);                   // ������ÿҳ��ʾ20���ˣ���ǰҳ��ֵ���Զ�����,
 *                                          //     ʹ��ҳ��ԭ����ҳ��ʾͬ����������û��������Ի�
 *   setItem(33);                           // �������԰�ҳ���������ʾ��33����(��0��ʼ����)����һҳ
 * 
 *   // �߼����ܣ���һ���������ڡ����Ǿ���Ҫ��webҳ������ʾһ��������ҳ�룬���û�ѡ��
 *   //        ____________________________________________________________
 *   // ����:  <<     <       3     4    5    6    [7]    8    9    >    >>
 *   //        ^      ^                             ^               ^    ^
 *   //       ��һҳ ǰһҳ                       ��ǰҳ          ��һҳ ���һҳ
 *   //
 *   // �������Ӿ���һ����СΪ7�Ļ������ڣ���ǰҳ�뱻�����ܰ����м䣬���ǵ�ǰҳλ�ڿ�ͷ���β��
 *   // ʹ������ĵ��ã��Ϳ��Եõ�ָ����С�Ļ��������е�ҳ�����顣
 *   int[] slider = pg.getSlider(7);
 * 
 *   // ���������ж�ָ��ҳ���Ƿ���Ч�������ǵ�ǰҳ����Ч��ҳ����webҳ���ϲ���Ҫ���ӡ�
 *   if (pg.isDisabledPage(slider[i])) {
 *       show = "page " + slider[i];
 *   } else {
 *       show = "<a href=#> page " + slider[i] + " </a>";
 *   }
 * 
 *   // ����ֱ�Ӵ�ӡ��pg�����ڵ��Գ���
 *   System.out.println(pg);
 *   log.debug(pg);
 * 
 * ]]></pre>
 * </p>
 */
public class Paginator implements Serializable, Cloneable {
    private static final long serialVersionUID       = 3688506614705500726L;

    /** ÿҳĬ�ϵ�����(10)�� */
    public static final int   DEFAULT_ITEMS_PER_PAGE = 10;

    /** ��������Ĭ�ϵĴ�С(7)�� */
    public static final int   DEFAULT_SLIDER_SIZE    = 7;

    /** ��ʾ����δ֪(<code>Integer.MAX_VALUE</code>)�� */
    public static final int   UNKNOWN_ITEMS          = Integer.MAX_VALUE;

    // ״̬��
    private int               page;                                         // ��ǰҳ�롣(1-based)
    private int               items;                                        // �ܹ�����
    private int               itemsPerPage;                                 // ÿҳ������

    /**
     * ����һ����ҳ������ʼ����Ϊ���޴�<code>UNKNOWN_ITEMS</code>��Ĭ��ÿҳ��ʾ<code>10</code>�
     */
    public Paginator() {
        this(0);
    }

    /**
     * ����һ����ҳ������ʼ����Ϊ���޴�<code>UNKNOWN_ITEMS</code>��ָ��ÿҳ������
     *
     * @param itemsPerPage ÿҳ������
     */
    public Paginator(int itemsPerPage) {
        this(itemsPerPage, UNKNOWN_ITEMS);
    }

    /**
     * ����һ����ҳ������ʼ����Ϊ���޴�<code>UNKNOWN_ITEMS</code>��ָ��ÿҳ������
     *
     * @param itemsPerPage ÿҳ������
     * @param items ������
     */
    public Paginator(int itemsPerPage, int items) {
        this.items = (items >= 0) ? items : 0;
        this.itemsPerPage = (itemsPerPage > 0) ? itemsPerPage : DEFAULT_ITEMS_PER_PAGE;
        this.page = calcPage(0);
    }

    /**
     * ȡ����ҳ����
     *
     * @return ��ҳ��
     */
    public int getPages() {
        return (int) Math.ceil((double) items / itemsPerPage);
    }

    /**
     * ȡ�õ�ǰҳ��
     *
     * @return ��ǰҳ
     */
    public int getPage() {
        return page;
    }

    /**
     * ���ò�ȡ�õ�ǰҳ��ʵ�ʵĵ�ǰҳֵ��ȷ������ȷ�ķ�Χ�ڡ�
     *
     * @param page ��ǰҳ
     *
     * @return ���ú�ĵ�ǰҳ
     */
    public int setPage(int page) {
        return (this.page = calcPage(page));
    }

    /**
     * ȡ����������
     *
     * @return ������
     */
    public int getItems() {
        return items;
    }

    /**
     * ���ò�ȡ�������������ָ����������С��0���򱻿���0���Զ�������ǰҳ��ȷ����ǰҳֵ����ȷ�ķ�Χ�ڡ�
     *
     * @param items ������
     *
     * @return �����Ժ��������
     */
    public int setItems(int items) {
        this.items = (items >= 0) ? items : 0;
        setPage(page);
        return this.items;
    }

    /**
     * ȡ��ÿҳ������
     *
     * @return ÿҳ����
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * ���ò�ȡ��ÿҳ���������ָ����ÿҳ����С�ڵ���0����ʹ��Ĭ��ֵ<code>DEFAULT_ITEMS_PER_PAGE</code>�� ��������ǰҳʹ֮�ڸı�ÿҳ����ǰ����ʾ��ͬ���
     *
     * @param itemsPerPage ÿҳ����
     *
     * @return ���ú��ÿҳ����
     */
    public int setItemsPerPage(int itemsPerPage) {
        int tmp = this.itemsPerPage;

        this.itemsPerPage = (itemsPerPage > 0) ? itemsPerPage : DEFAULT_ITEMS_PER_PAGE;

        if (page > 0) {
            setPage((int) (((double) (page - 1) * tmp) / this.itemsPerPage) + 1);
        }

        return this.itemsPerPage;
    }

    /**
     * ȡ�õ�ǰҳ��һ����ȫ�����е�ƫ���� (0-based)��
     *
     * @return ƫ����
     */
    public int getOffset() {
        return (page > 0) ? (itemsPerPage * (page - 1)) : 0;
    }

    /**
     * ȡ�õ�ǰҳ�ĳ��ȣ�����ǰҳ��ʵ���������൱�� <code>endIndex() - beginIndex() + 1</code>
     *
     * @return ��ǰҳ�ĳ���
     */
    public int getLength() {
        if (page > 0) {
            return Math.min(itemsPerPage * page, items) - (itemsPerPage * (page - 1));
        } else {
            return 0;
        }
    }

    /**
     * ȡ�õ�ǰҳ��ʾ�������ʼ��� (1-based)��
     *
     * @return ��ʼ���
     */
    public int getBeginIndex() {
        if (page > 0) {
            return (itemsPerPage * (page - 1)) + 1;
        } else {
            return 0;
        }
    }

    /**
     * ȡ�õ�ǰҳ��ʾ��ĩ����� (1-based)��
     *
     * @return ĩ�����
     */
    public int getEndIndex() {
        if (page > 0) {
            return Math.min(itemsPerPage * page, items);
        } else {
            return 0;
        }
    }

    /**
     * ���õ�ǰҳ��ʹ֮��ʾָ��offset(0-based)���
     *
     * @param itemOffset Ҫ��ʾ�����ƫ����(0-based)
     *
     * @return ָ�������ڵ�ҳ
     */
    public int setItem(int itemOffset) {
        return setPage((itemOffset / itemsPerPage) + 1);
    }

    /**
     * ȡ����ҳҳ�롣
     *
     * @return ��ҳҳ��
     */
    public int getFirstPage() {
        return calcPage(1);
    }

    /**
     * ȡ��ĩҳҳ�롣
     *
     * @return ĩҳҳ��
     */
    public int getLastPage() {
        return calcPage(getPages());
    }

    /**
     * ȡ��ǰһҳҳ�롣
     *
     * @return ǰһҳҳ��
     */
    public int getPreviousPage() {
        return calcPage(page - 1);
    }

    /**
     * ȡ��ǰnҳҳ��
     *
     * @param n ǰnҳ
     *
     * @return ǰnҳҳ��
     */
    public int getPreviousPage(int n) {
        return calcPage(page - n);
    }

    /**
     * ȡ�ú�һҳҳ�롣
     *
     * @return ��һҳҳ��
     */
    public int getNextPage() {
        return calcPage(page + 1);
    }

    /**
     * ȡ�ú�nҳҳ�롣
     *
     * @param n ��n��
     *
     * @return ��nҳҳ��
     */
    public int getNextPage(int n) {
        return calcPage(page + n);
    }

    /**
     * �ж�ָ��ҳ���Ƿ񱻽�ֹ��Ҳ����˵ָ��ҳ�볬���˷�Χ����ڵ�ǰҳ�롣
     *
     * @param page ҳ��
     *
     * @return boolean  �Ƿ�Ϊ��ֹ��ҳ��
     */
    public boolean isDisabledPage(int page) {
        return ((page < 1) || (page > getPages()) || (page == this.page));
    }

    /**
     * ȡ��Ĭ�ϴ�С(<code>DEFAULT_SLIDER_SIZE</code>)��ҳ�뻬�����ڣ�������ǰҳ�����ܵط��ڻ������ڵ��м䲿λ���μ�{@link #getSlider(int
     * n)}��
     *
     * @return ����ҳ�������
     */
    public int[] getSlider() {
        return getSlider(DEFAULT_SLIDER_SIZE);
    }

    /**
     * ȡ��ָ����С��ҳ�뻬�����ڣ�������ǰҳ�����ܵط��ڻ������ڵ��м䲿λ������: �ܹ���13ҳ����ǰҳ�ǵ�5ҳ��ȡ��һ����СΪ5�Ļ������ڣ������� 3��4��5��6,
     * 7�⼸��ҳ�룬��5ҳ�������м䡣�����ǰҳ��12���򷵻�ҳ��Ϊ 9��10��11��12��13��
     *
     * @param width �������ڴ�С
     *
     * @return ����ҳ������飬���ָ���������ڴ�СС��1����ҳ��Ϊ0���򷵻ؿ����顣
     */
    public int[] getSlider(int width) {
        int pages = getPages();

        if ((pages < 1) || (width < 1)) {
            return new int[0];
        } else {
            if (width > pages) {
                width = pages;
            }

            int[] slider = new int[width];
            int first = page - ((width - 1) / 2);

            if (first < 1) {
                first = 1;
            }

            if (((first + width) - 1) > pages) {
                first = pages - width + 1;
            }

            for (int i = 0; i < width; i++) {
                slider[i] = first + i;
            }

            return slider;
        }
    }

    /**
     * ����ҳ���������ı䵱ǰҳ��
     *
     * @param page ҳ��
     *
     * @return ������ȷ��ҳ��(��֤������߽�)
     */
    protected int calcPage(int page) {
        int pages = getPages();

        if (pages > 0) {
            return (page < 1) ? 1 : ((page > pages) ? pages : page);
        }

        return 0;
    }

    /**
     * ����������
     *
     * @return ����
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (java.lang.CloneNotSupportedException e) {
            return null; // �����ܷ���
        }
    }

    /**
     * ת�����ַ�����ʾ��
     *
     * @return �ַ�����ʾ��
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("Paginator: page ");

        if (getPages() < 1) {
            sb.append(getPage());
        } else {
            int[] slider = getSlider();

            for (int i = 0; i < slider.length; i++) {
                if (isDisabledPage(slider[i])) {
                    sb.append('[').append(slider[i]).append(']');
                } else {
                    sb.append(slider[i]);
                }

                if (i < (slider.length - 1)) {
                    sb.append('\t');
                }
            }
        }

        sb.append(" of ").append(getPages()).append(",\n");
        sb.append("    Showing items ").append(getBeginIndex()).append(" to ")
            .append(getEndIndex()).append(" (total ").append(getItems()).append(" items), ");
        sb.append("offset=").append(getOffset()).append(", length=").append(getLength());

        return sb.toString();
    }
}
