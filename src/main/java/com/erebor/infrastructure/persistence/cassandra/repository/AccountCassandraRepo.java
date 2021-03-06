package com.erebor.infrastructure.persistence.cassandra.repository;

import com.erebor.infrastructure.persistence.cassandra.table.AccountCassandraTable;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface AccountCassandraRepo extends CassandraRepository<AccountCassandraTable, AccountCassandraTable.Key> {

	@Query("truncate table " + AccountCassandraTable.ACCOUNTS_TABLE)
	void truncate();

}
