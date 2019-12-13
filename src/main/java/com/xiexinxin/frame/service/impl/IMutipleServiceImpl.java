package com.xiexinxin.frame.service.impl;

import com.xiexinxin.frame.holder.ApplicationContextHolder;
import com.xiexinxin.frame.modal.GenericServiceRequest;
import com.xiexinxin.frame.modal.GenericServiceResult;
import com.xiexinxin.frame.modal.GenericServiceResultList;
import com.xiexinxin.frame.service.IMutipleService;
import com.xiexinxin.frame.service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class IMutipleServiceImpl implements IMutipleService {
    @Override
    public GenericServiceResultList doMutipleService(List<GenericServiceRequest> requestList) {
        GenericServiceResultList resultList = new GenericServiceResultList();
        List<GenericServiceResult> genericServiceResultList = new ArrayList<>();
        if (requestList.size() > 1) {
            CountDownLatch latch = new CountDownLatch(1);
            for (int i = 0; i < requestList.size(); i++) {
                new Thread(new IMutipleServiceImpl.ServiceThread(requestList.get(i), genericServiceResultList, latch, requestList.size(), i)).start();
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            GenericServiceRequest request = requestList.get(0);
            IService service = ApplicationContextHolder.getContext().getBean(GenericServiceImpl.class);
            GenericServiceResult result = service.doService(request);
            genericServiceResultList.add(result);
        }
        resultList.setGenericServiceResultList(genericServiceResultList);
        return resultList;
    }


    class ServiceThread implements Runnable {
        GenericServiceRequest request = null;
        List<GenericServiceResult> resultList = null;
        CountDownLatch latch = null;
        int size;
        int index;

        public ServiceThread(GenericServiceRequest request, List<GenericServiceResult> resultList, CountDownLatch countDownLatch, int size, int index) {
            this.request = request;
            this.resultList = resultList;
            this.latch = countDownLatch;
            this.size = size;
            this.index = index;
        }

        @Override
        public void run() {
            IService service = ApplicationContextHolder.getContext().getBean(GenericServiceImpl.class);
            GenericServiceResult result = service.doService(request);
            this.resultList.add(result);
            if (this.index == this.size) {
                latch.countDown();
            }
        }
    }
}
