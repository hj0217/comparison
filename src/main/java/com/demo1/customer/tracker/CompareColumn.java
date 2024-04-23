package com.demo1.customer.tracker;

public enum CompareColumn {

        NAME("이름"),
        AGE("나이");

        private String value;

        CompareColumn(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
}


