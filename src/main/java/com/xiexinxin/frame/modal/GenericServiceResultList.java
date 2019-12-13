package com.xiexinxin.frame.modal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class GenericServiceResultList implements Serializable {
    private List<GenericServiceResult> genericServiceResultList;

    public List<GenericServiceResult> getGenericServiceResultList() {
        return genericServiceResultList;
    }

    public void setGenericServiceResultList(List<GenericServiceResult> genericServiceResultList) {
        this.genericServiceResultList = genericServiceResultList;
    }

    public String buildJSONString() {
        StringBuffer jsonString = new StringBuffer();
        jsonString.append("[");
        Iterator<GenericServiceResult> iterator = genericServiceResultList.iterator();
        while (iterator.hasNext()) {
            GenericServiceResult next = iterator.next();
            jsonString.append(next.buildJSONString());
        }
        jsonString.append("]");
        return jsonString.toString();
    }
}
