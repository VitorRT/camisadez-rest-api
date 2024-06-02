package br.com.camisadez.rest.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.camisadez.rest.api.dto.response.MessageResponse;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskFullResponse;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskPayload;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskPayloadByList;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskSimpleResponse;
import br.com.camisadez.rest.api.entity.DayEntity;
import br.com.camisadez.rest.api.entity.TodoTaskEntity;
import br.com.camisadez.rest.api.exception.ActionNotPermittedException;
import br.com.camisadez.rest.api.utils.DateUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import br.com.camisadez.rest.api.repository.DayRepository;
import br.com.camisadez.rest.api.repository.TodoTaskRepository;

/**
 * Classe <code>TaskService</code>
 *
 * Essa classe concentra todos os serviços relacionados ao recurso de sub-tarefas.
 * */
@Service
public class TodoTaskService {
	/**
	 * Dependências.
	 * */
	private TodoTaskRepository todoRepository;
	private DayRepository dayRepository;
	
	/**
	 * Construtor da classe
	 * */
	public TodoTaskService(DayRepository dayRepository, TodoTaskRepository todoRepository) {
		this.todoRepository = todoRepository;
		this.dayRepository = dayRepository;
	}
	
	
	/**
	 * Serviços
	 * */
	
	public TodoTaskFullResponse doCreateTodoTask(TodoTaskPayload payload, boolean bList) {
		DayEntity taskParent = new DayEntity();
		if(!bList)
			taskParent = getTaskById(payload.getDayParent().getId());

		List<TodoTaskEntity> overlappingTasks = todoRepository.findOverlappingTasks(payload.getStart(), payload.getEnd());

		if (!overlappingTasks.isEmpty()) {
			throw new IllegalArgumentException("O período de tempo já está ocupado por outra tarefa.");
		}

		TodoTaskEntity entity = new TodoTaskEntity();
		entity.prepareToCreate();
		entity.setTodoName(payload.getTodoName());
		entity.setTodoColor(payload.getTodoColor());
		entity.setTodoDesc(payload.getTodoDesc());
		entity.setStart(payload.getStart());
		entity.setEnd(payload.getEnd());

		TodoTaskEntity persited = todoRepository.saveAndFlush(entity);

		return new TodoTaskFullResponse(persited,taskParent);
	}

	public MessageResponse doCreateTodoTaskByList(TodoTaskPayloadByList payload) {
		if(payload.getTodoList().isEmpty())
			throw new RuntimeException("A lista de a fazares é obrigatória");

		getTaskById(payload.getDayParent().getId());

		DayEntity taskParent = getTaskById(payload.getDayParent().getId());
		for(TodoTaskPayload p : payload.getTodoList()) {
			p.setDayParent(payload.getDayParent());
			doCreateTodoTask(p, true);
		}

		String responseMessage = "A fazeres criados com sucesso!";
		return new MessageResponse(responseMessage);
	}
	
	public TodoTaskFullResponse doUpdateTodoTask(UUID todoId, TodoTaskPayload payload) {
		TodoTaskEntity entity = getTodoTaskById(todoId);
		DayEntity taskParent = getTaskById(payload.getDayParent().getId());

		List<TodoTaskEntity> overlappingTasks = todoRepository.findOverlappingTasks(payload.getStart(), payload.getEnd());

		if (!overlappingTasks.isEmpty()) {
			throw new IllegalArgumentException("O período de tempo já está ocupado por outra tarefa.");
		}

		entity.prepareToCreate();
		entity.setTodoName(payload.getTodoName());
		entity.setTodoColor(payload.getTodoColor());
		entity.setTodoDesc(payload.getTodoDesc());
		entity.setStart(payload.getStart());
		entity.setEnd(payload.getEnd());

		TodoTaskEntity persited = todoRepository.saveAndFlush(entity);
		TodoTaskFullResponse response = new TodoTaskFullResponse(persited,taskParent);

		response.setEnabledToComplete(DateUtils.isPastDate(taskParent.getDateOfTheDay()));
		return response;
	}
	
	public TodoTaskFullResponse getMyTodoTask(UUID todoId) {
		TodoTaskEntity entity = getTodoTaskById(todoId);
		DayEntity taskParent = getTaskByTodoTaskId(todoId);
		return new TodoTaskFullResponse(entity,taskParent);
	}
	
	public List<TodoTaskSimpleResponse> getAllMyTodoTasks(UUID taskId) {
		return todoRepository.getAllTasksTodoTasks(taskId).stream().map(TodoTaskSimpleResponse::new).toList();
	}
	
	public void doDeleteMyTask(UUID todoId) {
		TodoTaskEntity entity = getTodoTaskById(todoId);
		todoRepository.delete(entity);
	}

	public MessageResponse doCompleteTodo(UUID todoId) {
		TodoTaskEntity entity = getTodoTaskById(todoId);
		DayEntity taskParent = getTaskByTodoTaskId(todoId);
		if(DateUtils.isPastDate(taskParent.getDateOfTheDay()))
			throw new ActionNotPermittedException("Não é possível completar uma tarefa do passado.");

		entity.setHasBeenCompleted(true);
		TodoTaskEntity persisted = todoRepository.saveAndFlush(entity);

		String responseMessage = "O a fazer '" + persisted.getTodoName() + "' foi completado!";
		return new MessageResponse(responseMessage);
	}

	public MessageResponse doUncompleteTodo(UUID todoId) {
		TodoTaskEntity entity = getTodoTaskById(todoId);
		DayEntity taskParent = getTaskByTodoTaskId(todoId);
		if(DateUtils.isPastDate(taskParent.getDateOfTheDay()))
			throw new ActionNotPermittedException("Não é possível descompletar uma tarefa do passado.");

		entity.setHasBeenCompleted(true);
		TodoTaskEntity persisted = todoRepository.saveAndFlush(entity);

		String responseMessage = "O a fazer '" + persisted.getTodoName() + "' foi descompletado!";
		return new MessageResponse(responseMessage);
	}

	/**
	 * Utils methods.
	 * */

	/**
	 * Retorna uma task pelo 'id'.
	 * Esse método lança exceções de <code>{@link jakarta.persistence.EntityNotFoundException}</code>.
	 *
	 * @param id
	 * @return task
	 * */
	private DayEntity getTaskById(UUID id) {
		Optional<DayEntity> op =  dayRepository.findById(id);
		if(op.isEmpty())
			throw new EntityNotFoundException("Nenhuma tarefa foi encontrado com esse id.");
		return op.get();
	}

	/**
	 * Retorna uma sub task pelo 'id'.
	 * Esse método lança exceções de <code>{@link jakarta.persistence.EntityNotFoundException}</code>.
	 *
	 * @param id
	 * @return sub task
	 * */
	private TodoTaskEntity getTodoTaskById(UUID id) {
		Optional<TodoTaskEntity> op =  todoRepository.findById(id);
		if(op.isEmpty())
			throw new EntityNotFoundException("Nenhum a fazer foi encontrado com esse id.");
		return op.get();
	}

	private DayEntity getTaskByTodoTaskId(UUID todoId) {
		Optional<DayEntity> op =  dayRepository.getTaskByTodoDayId(todoId);
		if(op.isEmpty())
			throw new EntityNotFoundException("Nenhuma tarefa foi encontrado com esse id.");
		return op.get();
	}
}
