package ru.moysclad.view;

import io.swagger.annotations.ApiModelProperty;
import ru.moysclad.model.Account;

public class View {
    @ApiModelProperty(hidden = true)

    public Long id;

    public Long name;

    public Long sum = 0L;

    public View(){

    }

    public View(Long name, Long sum ){
        this.name = name;
        this.sum = sum;
    }

    public Account viewConvert(Account acc){
        acc.setName(name);
        acc.setSum(sum);

        return acc;
    }

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name+ ";sum:" + sum + "}";
    }
}
