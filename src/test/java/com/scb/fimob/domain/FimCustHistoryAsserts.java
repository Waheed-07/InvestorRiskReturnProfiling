package com.scb.fimob.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class FimCustHistoryAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFimCustHistoryAllPropertiesEquals(FimCustHistory expected, FimCustHistory actual) {
        assertFimCustHistoryAutoGeneratedPropertiesEquals(expected, actual);
        assertFimCustHistoryAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFimCustHistoryAllUpdatablePropertiesEquals(FimCustHistory expected, FimCustHistory actual) {
        assertFimCustHistoryUpdatableFieldsEquals(expected, actual);
        assertFimCustHistoryUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFimCustHistoryAutoGeneratedPropertiesEquals(FimCustHistory expected, FimCustHistory actual) {
        assertThat(expected)
            .as("Verify FimCustHistory auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFimCustHistoryUpdatableFieldsEquals(FimCustHistory expected, FimCustHistory actual) {
        assertThat(expected)
            .as("Verify FimCustHistory relevant properties")
            .satisfies(e -> assertThat(e.getCustId()).as("check custId").isEqualTo(actual.getCustId()))
            .satisfies(e -> assertThat(e.getHistoryTs()).as("check historyTs").isEqualTo(actual.getHistoryTs()))
            .satisfies(e -> assertThat(e.getClientId()).as("check clientId").isEqualTo(actual.getClientId()))
            .satisfies(e -> assertThat(e.getIdType()).as("check idType").isEqualTo(actual.getIdType()))
            .satisfies(e -> assertThat(e.getCtryCode()).as("check ctryCode").isEqualTo(actual.getCtryCode()))
            .satisfies(e -> assertThat(e.getCreatedBy()).as("check createdBy").isEqualTo(actual.getCreatedBy()))
            .satisfies(e -> assertThat(e.getCreatedTs()).as("check createdTs").isEqualTo(actual.getCreatedTs()))
            .satisfies(e -> assertThat(e.getUpdatedBy()).as("check updatedBy").isEqualTo(actual.getUpdatedBy()))
            .satisfies(e -> assertThat(e.getUpdatedTs()).as("check updatedTs").isEqualTo(actual.getUpdatedTs()))
            .satisfies(e -> assertThat(e.getRecordStatus()).as("check recordStatus").isEqualTo(actual.getRecordStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertFimCustHistoryUpdatableRelationshipsEquals(FimCustHistory expected, FimCustHistory actual) {}
}
