import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimSettAcctHistory } from 'app/shared/model/FIM/fim-sett-acct-history.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-sett-acct-history.reducer';

export const FimSettAcctHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimSettAcctHistoryEntity = useAppSelector(state => state.fim.fimSettAcctHistory.entity);
  const loading = useAppSelector(state => state.fim.fimSettAcctHistory.loading);
  const updating = useAppSelector(state => state.fim.fimSettAcctHistory.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimSettAcctHistory.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-sett-acct-history');
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
      ...fimSettAcctHistoryEntity,
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
          ...fimSettAcctHistoryEntity,
          historyTs: convertDateTimeFromServer(fimSettAcctHistoryEntity.historyTs),
          createdTs: convertDateTimeFromServer(fimSettAcctHistoryEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimSettAcctHistoryEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimSettAcctHistory.home.createOrEditLabel" data-cy="FimSettAcctHistoryCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimSettAcctHistory.home.createOrEditLabel">Create or edit a FimSettAcctHistory</Translate>
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
                  id="fim-sett-acct-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.settaccId')}
                id="fim-sett-acct-history-settaccId"
                name="settaccId"
                data-cy="settaccId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.historyTs')}
                id="fim-sett-acct-history-historyTs"
                name="historyTs"
                data-cy="historyTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.accountId')}
                id="fim-sett-acct-history-accountId"
                name="accountId"
                data-cy="accountId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.settAcctNbr')}
                id="fim-sett-acct-history-settAcctNbr"
                name="settAcctNbr"
                data-cy="settAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.settCcy')}
                id="fim-sett-acct-history-settCcy"
                name="settCcy"
                data-cy="settCcy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.settAcctStatus')}
                id="fim-sett-acct-history-settAcctStatus"
                name="settAcctStatus"
                data-cy="settAcctStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.createdBy')}
                id="fim-sett-acct-history-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.createdTs')}
                id="fim-sett-acct-history-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.updatedBy')}
                id="fim-sett-acct-history-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.updatedTs')}
                id="fim-sett-acct-history-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctHistory.recordStatus')}
                id="fim-sett-acct-history-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-sett-acct-history" replace color="info">
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

export default FimSettAcctHistoryUpdate;
