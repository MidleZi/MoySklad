package ru.moysclad.view;

import io.swagger.annotations.ApiModelProperty;
import ru.moysclad.model.Account;

public class View {
    @ApiModelProperty(hidden = true)

    public String id;

    public Long sum = 0L;

    public View(){

    }

    public View(Long sum ){
        this.sum = sum;
    }

    public Account viewConvert(Account acc){
        acc.setSum(sum);

        return acc;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";sum:" + sum + "}";
    }
}
