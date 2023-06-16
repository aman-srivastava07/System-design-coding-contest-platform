package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) 
    {
        System.out.println(userService.create(tokens.get(1)));
        //From UserService class humne yeh kia
    }
    // Interface ka agr object hey (obv null wala hi hoga) toh tum os object se kisi bhi class ka
    //method call kr skte ho, jo os interface ko implements kr rhi hey.
    
}
