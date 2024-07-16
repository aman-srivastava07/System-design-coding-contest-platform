package com.crio.codingame.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.entities.User;
import com.crio.codingame.repositories.IQuestionRepository;
import com.crio.codingame.repositories.QuestionRepository;
import com.crio.codingame.services.IContestService;
import com.crio.codingame.services.IQuestionService;
import com.crio.codingame.services.QuestionService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    //ME
    //IQuestionService iQuestionService;
    //User creator;
    //QuestionService questionService = new QuestionService(new QuestionRepository());
     //Implement above using 3 lines below
    // public QuestionService(IQuestionRepository questionRepository) {
     //   this.questionRepository = questionRepository;
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }
   
    

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]


    @Override
    public void execute(List<String> tokens) 
    {
        try 
        {
            if(tokens.size() == 1){
                List<Contest> contestList = contestService.getAllContestLevelWise(null);
                System.out.println(contestList);
                return;
            }
            else
            {
                String level = tokens.get(1);
                List<Contest> contestList = contestService.getAllContestLevelWise(Level.valueOf(level));
                //List<Question> qlist = questionService.getAllQuestionLevelWise(Level.valueOf(level)) ;
                //for(Contest contest: contestList)
                //{
                    //System.out.println(contest);
                   //System.out.println("Contest [id=" + contest.getId() + ", name=" + contest.getName() + ", level=" + contest.getLevel() + ", creator=" + contest.getCreator() + ", contestStatus=" + contest.getContestStatus() + ", questions=" + contest.getQuestions() + "]");
                   //List<Question> qlist = new ArrayList<>(contest.getQuestions());
                System.out.println(contestList);
                //}
    
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        
       
        //System.out.println(contestList);

        // List<Question> q_list = iQuestionService.getAllQuestionLevelWise(Level.valueOf(level));
        // List<Question> cont_list = q_list.stream()
        //                             .filter(e -> e.getLevel() == Level.valueOf(level))
        //                             .collect(Collectors.toList());
        // System.out.println(cont_list);
        
    }
    
}
