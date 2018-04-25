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
            if(view.name == null) throw new ControllerException();
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
            if(view.name == null) throw new ControllerException();
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
    @RequestMapping(value = "/{name}", method = {GET})
    public Response balance(@PathVariable Long name) {
        logger.info("Account get by name:" + name);
        try {
            if(name == null) throw new ControllerException();
            Object data = service.balance(name);

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
    @RequestMapping(value = "/{name}", method = {DELETE})
    public Response delete(@PathVariable Long name) {
        logger.info("Account name " + name + " deleted");

        try {
            if(name == null) throw new ControllerException();
            service.delete(name);

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
