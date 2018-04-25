package ru.moysclad.controller.impl;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import ru.moysclad.controller.Controller;
import ru.moysclad.service.Services;
import ru.moysclad.utils.ControllerException;
import ru.moysclad.utils.Response;
import ru.moysclad.utils.ResponseViewData;
import ru.moysclad.utils.ResponseViewError;
import ru.moysclad.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/bankaccount", produces = APPLICATION_JSON_VALUE)
public class ControllerImpl implements Controller {
    protected static Logger logger = Logger.getLogger(ControllerImpl.class.getName());
    private final Services service;

    @Autowired
    public ControllerImpl(Services service) {
        this.service = service;
    }


    @Override
    @ApiOperation(value = "createAccount", nickname = "createAccount", httpMethod = "POST")
    @RequestMapping(value = "/create", method = {POST})
    public Response create(@RequestBody View view) {
        try {
            char checkId[] = view.id.toCharArray();
            if(checkId.length != 5) throw new ControllerException("Номер аккаунта должен состоять из 5 цифр");
            for (int i = 0; i < checkId.length ; i++) {
                if (checkId[i] < '0' || checkId[i] > '9') throw new ControllerException("Номер аккаунта может состоять только из цифр");
            }
            service.create(view);
            logger.info("Account created: " + view.toString());

            return ResponseViewData.newBuilder()
                    .setData("success")
                    .build();


        }
        catch (Throwable e) {
            return ResponseViewError.newBuilder()
                    .setError(e.getMessage())
                    .build();
        }
    }

    @Override
    @ApiOperation(value = "depositOnAccount", nickname = "depositOnAccount", httpMethod = "PUT")
    @RequestMapping(value = "/deposit", method = {PUT})
    public Response deposit(@RequestBody View view) {
        try {
            if(view.id == null) throw new ControllerException("Не введен номер аккаунта");
            char checkId[] = view.id.toCharArray();
            if(checkId.length != 5) throw new ControllerException("Номер аккаунта должен состоять из 5 цифр");
            for (int i = 0; i < checkId.length ; i++) {
                if (checkId[i] < '0' || checkId[i] > '9') throw new ControllerException("Номер аккаунта может состоять только из цифр");
            }
            service.deposit(view);
            logger.info("Deposit on account " + view.toString());

            return ResponseViewData.newBuilder()
                    .setData("success")
                    .build();


        } catch (Throwable e) {
            return ResponseViewError.newBuilder()
                    .setError(e.getMessage())
                    .build();
        }
    }

    @Override
    @ApiOperation(value = "withdrawOnAccount", nickname = "withdrawOnAccount", httpMethod = "PUT")
    @RequestMapping(value = "/withdraw", method = {PUT})
    public Response withdraw(@RequestBody View view) {
        try {
            if(view.id == null) throw new ControllerException("Не введен номер аккаунта");
            char checkId[] = view.id.toCharArray();
            if(checkId.length != 5) throw new ControllerException("Номер аккаунта должен состоять из 5 цифр");
            for (int i = 0; i < checkId.length ; i++) {
                if (checkId[i] < '0' || checkId[i] > '9') throw new ControllerException("Номер аккаунта может состоять только из цифр");
            }
            service.withdraw(view);
            logger.info("Withdraw on account " + view.toString());

            return ResponseViewData.newBuilder()
                    .setData("success")
                    .build();


        } catch (Throwable e) {
            return ResponseViewError.newBuilder()
                    .setError(e.getMessage())
                    .build();
        }
    }

    @Override
    @ApiOperation(value = "getAccount", nickname = "getAccount", httpMethod = "GET")
    @RequestMapping(value = "/{id}", method = {GET})
    public Response balance(@PathVariable String id) {
        logger.info("Account get by name:" + id);
        try {
            if(id == null) throw new ControllerException("Не введен номер аккаунта");
            char checkId[] = id.toCharArray();
            if(checkId.length != 5) throw new ControllerException("Неверный адрес, номер аккаунта должен состоять из 5 цифр");
            for (int i = 0; i < checkId.length ; i++) {
                if (checkId[i] < '0' || checkId[i] > '9') throw new ControllerException("Номер аккаунта может состоять только из цифр");
            }
            Object data = service.balance(id);

            return ResponseViewData.newBuilder()
                    .setData(data)
                    .build();


        } catch (Throwable e) {
            return ResponseViewError.newBuilder()
                    .setError(e.getMessage())
                    .build();
        }
    }

    @Override
    @ApiOperation(value = "deleteAccount", nickname = "deleteAccount", httpMethod = "DELETE")
    @RequestMapping(value = "/{id}", method = {DELETE})
    public Response delete(@PathVariable String id) {
        logger.info("Account name " + id + " deleted");

        try {
            if(id == null) throw new ControllerException("Не введен номер аккаунта");
            char checkId[] = id.toCharArray();
            if(checkId.length != 5) throw new ControllerException("Номер аккаунта должен состоять из 5 цифр");
            for (int i = 0; i < checkId.length ; i++) {
                if (checkId[i] < '0' || checkId[i] > '9') throw new ControllerException("Номер аккаунта может состоять только из цифр");
            }
            service.delete(id);

            return ResponseViewData.newBuilder()
                    .setData("success")
                    .build();


        } catch (Throwable e) {
            return ResponseViewError.newBuilder()
                    .setError(e.getMessage())
                    .build();
        }
    }
}
