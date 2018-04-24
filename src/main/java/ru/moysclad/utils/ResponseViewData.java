package ru.moysclad.utils;

public class ResponseViewData implements Response {


    public Object data;


    private ResponseViewData() {
        // private constructor
    }

    public static Builder newBuilder() {
        return new ResponseViewData().new Builder();
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseView{" + "data=" + data + '}';
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setData(Object data) {
            ResponseViewData.this.data = data;

            return this;
        }

        public ResponseViewData build() {
            return ResponseViewData.this;
        }
    }
}
