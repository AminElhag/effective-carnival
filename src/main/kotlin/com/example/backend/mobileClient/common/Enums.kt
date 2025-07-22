package com.example.backend.mobileClient.common

enum class ContractType(id: Int) { MAIN(0), SECONDARY(1) }
enum class PaymentMethod(id: Int) { CREDIT_CARD(0), DIRECT_DEBIT(1), FRONT_DESK(2), COMPANY_INVOICE(3), MONEY_TRANSFER(4) }
enum class DiscountApplicationType(id: Int) { AUTOMATIC(0), MANUAL(1) }
enum class FreezeFeeType(id: Int) { FIXED(0), INTERVAL_PERCENTAGE(1), FREE(2) }
enum class ContractStatus(id: Int) { ACTIVE(0), FROZEN(1), TERMINATED(2), COMPLETED(3) }
enum class FreezeStatus(id: Int) { PENDING(0), ACTIVE(1), COMPLETED(2) }
enum class DiscountStatus(id: Int) { PENDING(0), ACTIVE(1), COMPLETED(2) }
enum class BillingCycle(id: Int) { DAILY(0), WEEKLY(1), MONTHLY(2), QUARTERLY(3), BIANNUAL(4), ANNUAL(5), LIFETIME(6) }
enum class PlanType(id: Int) { CONTRACT(0), CORPORATE(1), COURSE(2), NO_CONTRACT(3) }
enum class PeriodType(id: Int) { DAY(0), WEEK(1), MONTH(1), YEAR(3) }
enum class DiscountType(id: Int) { PERCENTAGE(0), FIXED_PRICE(1), AMOUNT(2) }
enum class Gender { MALE, FEMALE }
enum class EmploymentType { FULL_TIME, PART_TIME, CONTRACTOR, TEMPORARY }
enum class PaymentProvider { VISA, MASTERCARD, AMEX, DISCOVER, UNKNOWN }
enum class PaymentStatus { PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED, REFUNDED }