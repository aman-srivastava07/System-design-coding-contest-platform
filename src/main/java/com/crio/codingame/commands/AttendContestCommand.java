package com.crio.codingame.commands;


import java.util.List;
import java.util.Optional;
import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.ContestRepository;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.services.IUserService;

public class AttendContestCommand implements ICommand{

    private final IUserService userService;

    //My things
    //IContestRepository contestRepository ;
    IContestRepository contestRepository = new ContestRepository();
    Contest contest;
    
    public AttendContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute attendContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["ATTEND_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) 
    {
        
        // Optional<Optional<Contest>> contest = Optional.ofNullable(contestRepository.findById(tokens.get(1))); // throwing null ptr exception kyunki yeh object hi null wala bnaya hua hey humne. Dumbo
        // if(!contest.isPresent()) 
        // {
        //     System.out.println("Cannot Attend Contest. Contest for given id:"+tokens.get(1)+" not found!");
        // } 
        try
        {
            //Optional<Contest> contest = contestRepository.findById(tokens.get(1));
            //if(!contest.isPresent())
            //{
            //System.out.println("Cannot Attend Contest. Contest for given id:"+tokens.get(1)+" not found!");
            //System.out.println(userService.attendContest(tokens.get(1), tokens.get(2)));
            if(tokens.size() == 3)
            {
                UserRegistrationDto userRegistrationDto = userService.attendContest(tokens.get(1), tokens.get(2));
                System.out.println(userRegistrationDto);

            }
            
            
            //}

        }
        catch(ContestNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InvalidOperationException e)
        {
            System.out.println(e.getMessage());
        }
        
        //if(contestRepository.findById(tokens.get(arg0)))
        
        //UserRegistrationDto usd = userService.attendContest(tokens.get(1), tokens.get(2));  
        //System.out.println(userService.attendContest(tokens.get(1), tokens.get(2)));
    }
    
}

  
