import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimAccountsHistory } from 'app/shared/model/FIM/fim-accounts-history.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-accounts-history.reducer';

export const FimAccountsHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimAccountsHistoryEntity = useAppSelector(state => state.fim.fimAccountsHistory.entity);
  const loading = useAppSelector(state => state.fim.fimAccountsHistory.loading);
  const updating = useAppSelector(state => state.fim.fimAccountsHistory.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimAccountsHistory.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-accounts-history');
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
      ...fimAccountsHistoryEntity,
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
          ...fimAccountsHistoryEntity,
          historyTs: convertDateTimeFromServer(fimAccountsHistoryEntity.historyTs),
          createdTs: convertDateTimeFromServer(fimAccountsHistoryEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimAccountsHistoryEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimAccountsHistory.home.createOrEditLabel" data-cy="FimAccountsHistoryCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimAccountsHistory.home.createOrEditLabel">Create or edit a FimAccountsHistory</Translate>
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
                  id="fim-accounts-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.accountId')}
                id="fim-accounts-history-accountId"
                name="accountId"
                data-cy="accountId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.historyTs')}
                id="fim-accounts-history-historyTs"
                name="historyTs"
                data-cy="historyTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.custId')}
                id="fim-accounts-history-custId"
                name="custId"
                data-cy="custId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.relnId')}
                id="fim-accounts-history-relnId"
                name="relnId"
                data-cy="relnId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.relnType')}
                id="fim-accounts-history-relnType"
                name="relnType"
                data-cy="relnType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 5, message: translate('entity.validation.maxlength', { max: 5 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.operInst')}
                id="fim-accounts-history-operInst"
                name="operInst"
                data-cy="operInst"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.isAcctNbr')}
                id="fim-accounts-history-isAcctNbr"
                name="isAcctNbr"
                data-cy="isAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.bndAcctNbr')}
                id="fim-accounts-history-bndAcctNbr"
                name="bndAcctNbr"
                data-cy="bndAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.closingId')}
                id="fim-accounts-history-closingId"
                name="closingId"
                data-cy="closingId"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.subSegment')}
                id="fim-accounts-history-subSegment"
                name="subSegment"
                data-cy="subSegment"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.branchCode')}
                id="fim-accounts-history-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.acctStatus')}
                id="fim-accounts-history-acctStatus"
                name="acctStatus"
                data-cy="acctStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.ctryCode')}
                id="fim-accounts-history-ctryCode"
                name="ctryCode"
                data-cy="ctryCode"
                type="text"
                validate={{
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.acctOwners')}
                id="fim-accounts-history-acctOwners"
                name="acctOwners"
                data-cy="acctOwners"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.remarks')}
                id="fim-accounts-history-remarks"
                name="remarks"
                data-cy="remarks"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.createdBy')}
                id="fim-accounts-history-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.createdTs')}
                id="fim-accounts-history-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.updatedBy')}
                id="fim-accounts-history-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.updatedTs')}
                id="fim-accounts-history-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsHistory.recordStatus')}
                id="fim-accounts-history-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-accounts-history" replace color="info">
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

export default FimAccountsHistoryUpdate;
