package com.example.yebelo.service;

import com.example.yebelo.model.Number;
import com.example.yebelo.repo.NumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class NumberServiceImpl  implements NumberService{
    static boolean checkOneorNot(int n){
        int sum = 0;
        while(n!=0){
            sum+=n%10;
            n/=10;
        }
        return sum%10==0?true:false;
    }

    ArrayList<Integer> list = new ArrayList<>();

    @Autowired
    private NumberRepo numberRepo;

//    Object target;
//    Logger logger = LoggerFactory.getLogger(NumberServiceImpl.class);

    static int lower_bound(ArrayList<Integer> list, int N, int X)
    {
        int mid;

        // Initialise starting index and
        // ending index
        int low = 0;
        int high = N;

        // Till low is less than high
        while (low < high) {
            mid = low + (high - low) / 2;

            // If X is less than or equal
            // to arr[mid], then find in
            // left subarray
            if (X <= list.get(mid)) {
                high = mid;
            }

            // If X is greater arr[mid]
            // then find in right subarray
            else {
                low = mid + 1;
            }
        }

        // if X is greater than arr[n-1]
        if(low < N && list.get(low) < X) {
            low++;
        }

        // Return the lower_bound index
        return low;
    }

    private void sleep(int args){
        try{
            TimeUnit.SECONDS.sleep(args);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void getNumber(Number num) {
        final long start = System.currentTimeMillis();
        for(int i=num.getCategoryCode(); i<100000; i++){
            if(checkOneorNot(i)){
                list.add(i);
            }
        }
        System.out.println("Current Thread is:"+Thread.currentThread().getName());
        //it will search on the basis of new value that is previous
        int numberToSearch = num.getNewValue();
        if(numberToSearch==0 || list.size()==0){
            saveNumber(num,19);
            return;
        }
        int idx = lower_bound(list,list.size(),numberToSearch);
        if(idx==-1){
            saveNumber(num,19);
            return;
        }
        if(list.get(idx)==numberToSearch && idx<list.size()-1){
            saveNumber(num,list.get(++idx));
            return;
        }
        saveNumber(num, list.get(idx));
        System.out.println("Elapsed Time:"+"Current Thread is:"+Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() - start);
    }

    @Override
    public List<Number> findALLNumbers() {
        return numberRepo.findAll();
    }
    @Override
    public Optional<Number> findNById(int id){
        return numberRepo.findById(id);
    }
    //we will save the number also in the database
    public void saveNumber(Number num, int n){
        Optional<Number> byId = numberRepo.findById(num.getCategoryCode());
        if(byId.isPresent()){
            Number number = byId.get();
            number.setOldValue(num.getNewValue());
            number.setNewValue(n);
            numberRepo.save(number);
        }
        else{
            num.setNewValue(n);
            num.setOldValue(n);
            numberRepo.save(num);
        }
    }
}
