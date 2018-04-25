package ru.moysclad.controller;

import ru.moysclad.utils.Response;
import ru.moysclad.view.View;

public interface Controller {

   Response create(View view);

   Response deposit(View view);

   Response withdraw(View view);

   Response balance(String id);

   Response delete(String id);
}
