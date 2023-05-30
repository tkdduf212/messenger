//package com.noint.messenger.mq;
//
//import com.google.gson.Gson;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//@RequiredArgsConstructor
//@Log4j2
//public class RabbitQueue {
//    private final RabbitAdmin rabbitAdmin;
//    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;
//
//    public void addNewQueue(String queueName, String exchangeName, String routingKey) {
//        Queue queue = new Queue(queueName, false, false, true);
//        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
//        rabbitAdmin.declareQueue(queue);
//        rabbitAdmin.declareBinding(binding);
//        this.addQueueToListener(exchangeName, queueName);
//    }
//
//    public void addQueueToListener(String listenerId, String queueName) {
//        log.info("adding queue : " + queueName + " to listener with id : " + listenerId);
//        if (!checkQueueExistOnListener(listenerId, queueName)) {
//            this.getMessageListenerContainerById(listenerId).addQueueNames(queueName);
//            log.info("queue ");
//        } else {
//            log.info("given queue name : " + queueName + " not exist on given lister id : " + listenerId);
//        }
//    }
//
//    public void removeQueueFromListener(String listenerId, String queueName) {
//        log.info("removing queue : " + queueName + " form listener : " + listenerId);
//        if (checkQueueExistOnListener(listenerId, queueName)) {
//            this.getMessageListenerContainerById(listenerId).removeQueueNames(queueName);
//            log.info("deleting queue form rabbit management");
//            this.rabbitAdmin.deleteQueue(queueName);
//        } else {
//            log.info("given queue name : " + queueName + " not exist on given listener id : " + listenerId);
//        }
//    }
//    public Boolean checkQueueExistOnListener(String listenerId, String queueName) {
//        try {
//            log.info("checking queueName : " + queueName + " exist on listener id : " + listenerId);
//            log.info("getting queueNames");
//            String[] queueNames = this.getMessageListenerContainerById(listenerId).getQueueNames();
//            log.info("queueNames : " + new Gson().toJson(queueNames));
//            if (queueNames != null) {
//                log.info("checking " + queueName + " exist on active queues");
//                for (String name : queueNames) {
//                    log.info("name : " + name + " with checking name : " + queueName);
//                    if (name.equals(queueName)) {
//                        log.info("queue name exist on listener, returning true");
//                        return Boolean.TRUE;
//                    }
//                }
//                return Boolean.FALSE;
//            } else {
//                log.info("there is no queue exist on listener");
//                return Boolean.FALSE;
//            }
//        } catch (Exception e) {
//            log.error("Error on checking queue exist on listener");
//            log.error("error message : " + e.getMessage());
//            log.error("trace : " + e.getStackTrace());
//            return Boolean.FALSE;
//        }
//    }
//
//    private AbstractMessageListenerContainer getMessageListenerContainerById(String listenerId) {
//        log.info("getting message listener container by id : " + listenerId);
//        return (AbstractMessageListenerContainer) this.rabbitListenerEndpointRegistry.getListenerContainer(listenerId);
//    }
//}
