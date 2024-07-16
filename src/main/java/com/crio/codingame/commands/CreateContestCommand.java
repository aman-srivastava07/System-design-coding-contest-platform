package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IQuestionRepository;
import com.crio.codingame.repositories.IUserRepository;
import com.crio.codingame.repositories.QuestionRepository;
import com.crio.codingame.services.IContestService;
import com.crio.codingame.services.QuestionService;
import com.crio.codingame.services.UserService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    //Mine
    //QuestionService questionService = new QuestionService(new IQuestionRepository());
    // error-> Cannot instantiate the type IQuestionRepository
    //QuestionService questionService = new QuestionService(new QuestionRepository());

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW, "Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) 
    {
        // Case -> If numQuestion is Given
        //Contest contest = contestService.create(tokens.get(1), Level.valueOf(tokens.get(2)), tokens.get(3), Integer.parseInt(tokens.get(4)));
       
            //Contest contest = contestService.create(tokens.get(1), Level.valueOf(tokens.get(2).split(" ")[0]), tokens.get(2).split(" ")[1], Integer.valueOf(tokens.get(3)));
            //System.out.println(contest);

            try
            {
                //List<Question> qlist = questionService.getAllQuestionLevelWise(Level.valueOf(tokens.get(2)));
                if(tokens.size() == 5)
                {
                    Contest contest = contestService.create(tokens.get(1), Level.valueOf(tokens.get(2)), tokens.get(3),Integer.valueOf(tokens.get(4)));
                    System.out.println(contest);
                }
                else
                {
                    Contest contest = contestService.create(tokens.get(1), Level.valueOf(tokens.get(2)),tokens.get(3),null);
                    System.out.println(contest);

                }
                

            }
            catch(UserNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
            catch(QuestionNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
          
        
        
            

        //Contest contest = contestService.create(tokens.get(0).split(",")[1], Level.valueOf(tokens.get(0).split(",")[2].split(" ")[0]), tokens.get(0).split(",")[2].split(" ")[1], Integer.parseInt(tokens.get(0).split(",")[3]));  
    }
    
}
