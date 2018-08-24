package com.spring.boot.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Ranj on 2017/7/19 0019.
 */
public class BasePageResult<T> extends BaseResult implements Iterable<T>, Serializable {

    private Paginator paginator;
    private List<T> list;

    /**
     * 创建一个<code>PageList</code>。
     */
    public BasePageResult() {
        this(null, null);
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public BasePageResult(Collection<T> c) {
        this(c, null);
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public BasePageResult(Collection<T> c, Paginator paginator) {
        if (c == null) {
            c = new ArrayList<>();
        }
        if (paginator == null) {
            paginator = new Paginator(0, c.size());
        }
        this.list = new ArrayList<>(c);
        this.paginator = paginator;
    }

    /**
     * 取得分页器，可从中取得有关分页和页码的所有信息。
     *
     * @return 分页器对象
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * 设置分页器。
     *
     * @param paginator 要设置的分页器对象
     */
    public void setPaginator(Paginator paginator) {
        if (paginator != null) {
            this.paginator = paginator;
        }
    }

    public void setPaginator(PageInfo info) {
        if (info != null) {
            Paginator paginator1 = new Paginator();
            paginator1.setPageInfo(info);
            this.paginator = paginator1;
        }
    }


    /**
     * use getPaginator() instead
     */
    public int getPageSize() {
        return paginator.getItemsPerPage();
    }

    /**
     * use getPaginator() instead
     */
    public int getTotalItem() {
        return paginator.getItems();
    }

    /**
     * use getPaginator() instead
     */
    public int getTotalPage() {
        return paginator.getPages();
    }

    /**
     * use getPaginator() instead
     */
    public void setPageSize(int i) {
        paginator.setItemsPerPage(i);
    }

    /**
     * use getPaginator() instead
     */
    public void setTotalItem(int i) {
        paginator.setItems(i);
    }

    /**
     * use getPaginator() instead
     */
    public void setTotalPage(int i) {
        setTotalItem(i * getPageSize());
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    /**
     * 添加一个元素
     */
    public boolean add(T t) {
        this.list.add(t);
        resetPaginator();
        return true;
    }

    private void resetPaginator() {
    }

    /**
     * 添加集合
     */
    public boolean addAll(Collection<? extends T> c) {
        this.list.addAll(c);
        resetPaginator();
        return true;
    }

    public T get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }

    @JSONField(serialize = false)
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void add(int index, T element) {
        this.list.add(index, element);
        resetPaginator();
    }

    public boolean contains(T o) {
        return this.list.contains(o);
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public <E> E[] toArray(E[] a) {
        return this.list.toArray(a);
    }

    public boolean remove(T o) {
        boolean b = this.list.remove(o);
        if (b) {
            resetPaginator();
        }
        return b;
    }

    public void clear() {
        this.list.clear();
        resetPaginator();
    }

    public T set(int index, T element) {
        return this.list.set(index, element);
    }

    public T remove(int index) {
        T t = this.list.remove(index);
        resetPaginator();
        return t;
    }

    public int indexOf(T o) {
        return this.list.indexOf(o);
    }

    public int lastIndexOf(T o) {
        return this.list.lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return this.list.listIterator();
    }


    public ListIterator<T> listIterator(int index) {
        return this.list.listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasePageResult{");
        sb.append("list=").append(list);
        sb.append(", paginator=").append(paginator);
        sb.append('}');
        return sb.toString();
    }
}
