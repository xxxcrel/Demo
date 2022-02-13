package com.cheeseocean.spring;

public class Content {

    Text text;
    public Content(Text text){
        this.text = text;
    }

    public Text text(){
        return text;
    }

    public void print(){
        System.out.println("content: " + text);
    }
}
