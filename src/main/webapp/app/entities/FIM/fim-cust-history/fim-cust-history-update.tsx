import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimCustHistory } from 'app/shared/model/FIM/fim-cust-history.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-cust-history.reducer';

export const FimCustHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimCustHistoryEntity = useAppSelector(state => state.fim.fimCustHistory.entity);
  const loading = useAppSelector(state => state.fim.fimCustHistory.loading);
  const updating = useAppSelector(state => state.fim.fimCustHistory.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimCustHistory.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-cust-history');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.historyTs = convertDateTimeToServer(values.historyTs);
    values.createdTs = convertDateTimeToServer(values.createdTs);
    values.updatedTs = convertDateTimeToServer(values.updatedTs);

    const entity = {
      ...fimCustHistoryEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          historyTs: displayDefaultDateTime(),
          createdTs: displayDefaultDateTime(),
          updatedTs: displayDefaultDateTime(),
        }
      : {
          ...fimCustHistoryEntity,
          historyTs: convertDateTimeFromServer(fimCustHistoryEntity.historyTs),
          createdTs: convertDateTimeFromServer(fimCustHistoryEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimCustHistoryEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimCustHistory.home.createOrEditLabel" data-cy="FimCustHistoryCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimCustHistory.home.createOrEditLabel">Create or edit a FimCustHistory</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="fim-cust-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.custId')}
                id="fim-cust-history-custId"
                name="custId"
                data-cy="custId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.historyTs')}
                id="fim-cust-history-historyTs"
                name="historyTs"
                data-cy="historyTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.clientId')}
                id="fim-cust-history-clientId"
                name="clientId"
                data-cy="clientId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.idType')}
                id="fim-cust-history-idType"
                name="idType"
                data-cy="idType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.ctryCode')}
                id="fim-cust-history-ctryCode"
                name="ctryCode"
                data-cy="ctryCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.createdBy')}
                id="fim-cust-history-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.createdTs')}
                id="fim-cust-history-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.updatedBy')}
                id="fim-cust-history-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.updatedTs')}
                id="fim-cust-history-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCustHistory.recordStatus')}
                id="fim-cust-history-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-cust-history" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default FimCustHistoryUpdate;
