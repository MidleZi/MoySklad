package ru.moysclad.utils;

public class ResponseViewError implements Response {

    public String error;

    private ResponseViewError() {
        // private constructor
    }

    public static Builder newBuilder() {
        return new ResponseViewError().new Builder();
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ResponseView{" + ", error=" + error + '}';
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setError(String error) {
            ResponseViewError.this.error = error;

            return this;
        }

        public ResponseViewError build() {
            return ResponseViewError.this;
        }
    }
}
