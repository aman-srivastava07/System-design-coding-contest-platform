package com.crio.codingame.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.event.HyperlinkEvent;
import com.crio.codingame.dtos.ContestSummaryDto;
import java.util.List;
import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.

    //QuestionService.java
    @Override
    public User create(String name) 
    {
        User user = new User(name, 0);
        return userRepository.save(user);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    // Self by observing that repo ka object given tha 
    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder)
    {
        List<User> score_list = new ArrayList<>(); 
        score_list = userRepository.findAll();
        if(scoreOrder == scoreOrder.ASC)
            Collections.sort(score_list,Comparator.comparing(User::getScore));
        else
            Collections.sort(score_list,Comparator.comparing(User::getScore).reversed());
        
        return score_list;
    }
            //Collections.sort(userRepository.findAll(),Comparator.comparing(User::getScore),Collections.reverseOrder());
         
    // @Override
    // public User create(String name) {
    // }


    // @Override
    // public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
    // }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    //Self: hit and trial Upr waale fun se dekha (14 failed se ab 7 hi failed bache)
    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException 
    {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException());
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException());
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException();
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException();
        }
        if(user.checkIfContestExists(contest)){
            //throw new InvalidOperationException();
            
            user.deleteContest(contest);
            UserRegistrationDto urd = new UserRegistrationDto(contest.getName(), user.getName(), RegisterationStatus.NOT_REGISTERED);
            userRepository.save(user);
            //userRepository.save(user);
            //user.deleteContest(contest);
            //contestRepository.save(contest);
            return urd;
        }
        else
        {
            //contestRepository.delete(contest);
            //user.deleteContest(contest);
            //contestRepository.save(contest);
            //userRepository.save(user);
            throw new InvalidOperationException();
            
        }
    }
        
        //userRepository.save(user);
        
        // contest name hey
        // user name hey
        // registered hey ki nhi yeh pta lgana hey
        // List<User> user_list_from_repo = new ArrayList<>();
        // user_list_from_repo = userRepository.findAll();
        // if(user_list_from_repo.contains(user))
        // {
        //     return new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.NOT_REGISTERED);
            //Optional<Optional<Contest>> con = Optional.ofNullable(contestRepository.findById(contestId));
            //String contest_name = con.getName();
            //Optional<Contest> contest2 = contestRepository.findById(contestId);
            //String user_name = user.getName();
            //return new UserRegistrationDto(contest2.toString(), user_name, RegisterationStatus.NOT_REGISTERED);
        //}
            
        //else
        //throw new InvalidOperationException();


        // UserRegistrationDto urd = new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.REGISTERED);
        // if(urd.getRegisterationStatus() == RegisterationStatus.NOT_REGISTERED)
        //     throw new InvalidOperationException();
        // else
            
            

        //return new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.NOT_REGISTERED);
        //new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.NOT_REGISTERED);
        //UserRegistrationDto urd = new UserRegistrationDto(contest.getName(), user.getName(), registerationStatus)
        
        //return new UserRegistrationDto(contestId, userName, RegisterationStatus.NOT_REGISTERED);

    // @Override
    // public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    // }
    
}
