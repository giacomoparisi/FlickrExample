package com.giacomoparisi.domain.datatypes.uievent;

import java.util.ArrayList;
import java.util.List;

public class UIEvent<T> {

    private final T content;
    private Handle<T> handle;

    private List<String> handledBy = new ArrayList<>();


    public UIEvent(T content) {
        this.content = content;
        handle = new ToHandle<T>(this.content);
    }

    /**
     * Returns the content and prevents its use again from the same handler.
     */
     public Handle<T> getContentByHandler(String handlerId) {

         if(handle instanceof ToHandle) {
             if(!handledBy.contains(handlerId)) {
                 handledBy.add(handlerId);
                 return ((ToHandle<T>) handle);
             }
             else return new Handled<>();
         }
         else return new Handled<>();
     }

}
