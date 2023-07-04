package cn.foofun.jpa

import org.springframework.data.jpa.domain.Specification

/**
 * 组合Specification查询条件
 */
object JpaHelper {
    fun <T> and(spec1: Specification<T>?, spec2: Specification<T>?): Specification<T>? {
        if (spec1 == null && spec2 == null) {
            return null
        }
        if (spec1 == null) {
            return spec2
        }
        if (spec2 == null) {
            return spec1
        }

        return Specification<T> { root, query, criteriaBuilder ->
            criteriaBuilder.and(
                spec1.toPredicate(root, query, criteriaBuilder),
                spec2.toPredicate(root, query, criteriaBuilder),
            )
        }
    }

    fun <T> or(spec1: Specification<T>?, spec2: Specification<T>?): Specification<T>? {
        if (spec1 == null && spec2 == null) {
            return null
        }
        if (spec1 == null) {
            return spec2
        }
        if (spec2 == null) {
            return spec1
        }

        return Specification<T> { root, query, criteriaBuilder ->
            criteriaBuilder.or(
                spec1.toPredicate(root, query, criteriaBuilder),
                spec2.toPredicate(root, query, criteriaBuilder),
            )
        }
    }
}