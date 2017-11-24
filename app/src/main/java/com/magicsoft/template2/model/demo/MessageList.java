package com.magicsoft.template2.model.demo;


import java.util.List;

public class MessageList {

    /**
     * content : [{"id":"5a14dde036de73d7587b43bf","title":"sdfsdf","content":null,"abs":"fff","status":0,"type":null,"oid":null,"createTime":"2017-11-22 10:16:00","modifyTime":"2017-11-22 10:16:00","member":null},{"id":"5a14e47d36de73d450f05f62","title":"关于不法分子利用人脸识别盗取游戏帐号的公告 ","content":null,"abs":"近期收到用户反馈，不法分子盗取用户的个人信息，并利用至尊保的人脸识别功能进行游戏盗号","status":0,"type":null,"oid":null,"createTime":"2017-11-22 10:44:12","modifyTime":"2017-11-22 10:44:12","member":null}]
     * totalPages : 1
     * last : true
     * totalElements : 2
     * number : 0
     * size : 20
     * sort : null
     * first : true
     * numberOfElements : 2
     */

    private int totalPages;
    private boolean last;
    private int totalElements;
    private int number;
    private int size;
    private Object sort;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 5a14dde036de73d7587b43bf
         * title : sdfsdf
         * content : null
         * abs : fff
         * status : 0
         * type : null
         * oid : null
         * createTime : 2017-11-22 10:16:00
         * modifyTime : 2017-11-22 10:16:00
         * member : null
         */

        private String id;
        private String title;
        private Object content;
        private String abs;
        private int status;
        private Object type;
        private Object oid;
        private String createTime;
        private String modifyTime;
        private Object member;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getOid() {
            return oid;
        }

        public void setOid(Object oid) {
            this.oid = oid;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }
    }
}
