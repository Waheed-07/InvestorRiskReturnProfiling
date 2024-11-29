import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimAccounts } from 'app/shared/model/FIM/fim-accounts.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-accounts.reducer';

export const FimAccountsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimAccountsEntity = useAppSelector(state => state.fim.fimAccounts.entity);
  const loading = useAppSelector(state => state.fim.fimAccounts.loading);
  const updating = useAppSelector(state => state.fim.fimAccounts.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimAccounts.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-accounts');
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
    values.createdTs = convertDateTimeToServer(values.createdTs);
    values.updatedTs = convertDateTimeToServer(values.updatedTs);

    const entity = {
      ...fimAccountsEntity,
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
          createdTs: displayDefaultDateTime(),
          updatedTs: displayDefaultDateTime(),
        }
      : {
          ...fimAccountsEntity,
          createdTs: convertDateTimeFromServer(fimAccountsEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimAccountsEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimAccounts.home.createOrEditLabel" data-cy="FimAccountsCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimAccounts.home.createOrEditLabel">Create or edit a FimAccounts</Translate>
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
                  id="fim-accounts-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.accountId')}
                id="fim-accounts-accountId"
                name="accountId"
                data-cy="accountId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.custId')}
                id="fim-accounts-custId"
                name="custId"
                data-cy="custId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.relnId')}
                id="fim-accounts-relnId"
                name="relnId"
                data-cy="relnId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.relnType')}
                id="fim-accounts-relnType"
                name="relnType"
                data-cy="relnType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 5, message: translate('entity.validation.maxlength', { max: 5 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.operInst')}
                id="fim-accounts-operInst"
                name="operInst"
                data-cy="operInst"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.isAcctNbr')}
                id="fim-accounts-isAcctNbr"
                name="isAcctNbr"
                data-cy="isAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.bndAcctNbr')}
                id="fim-accounts-bndAcctNbr"
                name="bndAcctNbr"
                data-cy="bndAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.closingId')}
                id="fim-accounts-closingId"
                name="closingId"
                data-cy="closingId"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.subSegment')}
                id="fim-accounts-subSegment"
                name="subSegment"
                data-cy="subSegment"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.branchCode')}
                id="fim-accounts-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.acctStatus')}
                id="fim-accounts-acctStatus"
                name="acctStatus"
                data-cy="acctStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.ctryCode')}
                id="fim-accounts-ctryCode"
                name="ctryCode"
                data-cy="ctryCode"
                type="text"
                validate={{
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.acctOwners')}
                id="fim-accounts-acctOwners"
                name="acctOwners"
                data-cy="acctOwners"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.remarks')}
                id="fim-accounts-remarks"
                name="remarks"
                data-cy="remarks"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.createdBy')}
                id="fim-accounts-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.createdTs')}
                id="fim-accounts-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.updatedBy')}
                id="fim-accounts-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.updatedTs')}
                id="fim-accounts-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccounts.recordStatus')}
                id="fim-accounts-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-accounts" replace color="info">
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

export default FimAccountsUpdate;
