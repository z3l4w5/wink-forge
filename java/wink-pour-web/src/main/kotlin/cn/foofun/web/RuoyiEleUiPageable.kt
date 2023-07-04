package cn.foofun.web

import org.springframework.data.domain.Sort

/**
 * pageSize大于0，表示可客户端定制pageSize
 * @see RuoyiEleUiPageableResolver
 */
annotation class RuoyiEleUiPageable(
    val pageSize: Int = 10,
    val defaultPageSize: Int = 10,
    val sort: Array<String> = [],
    val direction: Sort.Direction = Sort.Direction.ASC
)
