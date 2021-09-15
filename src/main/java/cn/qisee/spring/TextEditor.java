package cn.qisee.spring;

import java.beans.PropertyEditorSupport;

public class TextEditor extends PropertyEditorSupport {
    @Override
    public void setValue(Object value) {
       if (value instanceof Content){
           super.setValue(((Content) value).text());
       }
    }
}
