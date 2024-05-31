package com.example.todolist.task.model;

import com.example.todolist.common.exception.CommonException;
import com.example.todolist.common.exception.CommonExceptionCode;
import lombok.Getter;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Getter
public enum TaskStatus {
    IN_PROGRESS {
        @Override
        protected boolean changeValidation(TaskStatus status) {
            return PENDING.equals(status) || DONE.equals(status);
        }
    },
    DONE {
    },
    PENDING {
        @Override
        protected boolean changeValidation(TaskStatus status) {
            return true;
        }
    };

    public final TaskStatus validation(final TaskStatus to) {
        if (changeValidation(to)) {
            return to;
        }
        throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
    }

    // 상태별로 업데이트 가능여부 체크
    protected boolean changeValidation(TaskStatus status) {
        return false;
    }
}
