package com.example.backend.mobileClient.features.contract.repository

import com.example.backend.mobileClient.features.contract.repository.entity.Contract
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository: CrudRepository<Contract, String> {
}