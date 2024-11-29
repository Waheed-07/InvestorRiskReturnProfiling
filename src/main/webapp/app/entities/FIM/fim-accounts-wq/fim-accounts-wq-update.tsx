import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimAccountsWq } from 'app/shared/model/FIM/fim-accounts-wq.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-accounts-wq.reducer';

export const FimAccountsWqUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimAccountsWqEntity = useAppSelector(state => state.fim.fimAccountsWq.entity);
  const loading = useAppSelector(state => state.fim.fimAccountsWq.loading);
  const updating = useAppSelector(state => state.fim.fimAccountsWq.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimAccountsWq.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-accounts-wq');
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
      ...fimAccountsWqEntity,
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
          ...fimAccountsWqEntity,
          createdTs: convertDateTimeFromServer(fimAccountsWqEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimAccountsWqEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimAccountsWq.home.createOrEditLabel" data-cy="FimAccountsWqCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimAccountsWq.home.createOrEditLabel">Create or edit a FimAccountsWq</Translate>
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
                  id="fim-accounts-wq-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.accountId')}
                id="fim-accounts-wq-accountId"
                name="accountId"
                data-cy="accountId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.custId')}
                id="fim-accounts-wq-custId"
                name="custId"
                data-cy="custId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.relnId')}
                id="fim-accounts-wq-relnId"
                name="relnId"
                data-cy="relnId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.relnType')}
                id="fim-accounts-wq-relnType"
                name="relnType"
                data-cy="relnType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 5, message: translate('entity.validation.maxlength', { max: 5 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.operInst')}
                id="fim-accounts-wq-operInst"
                name="operInst"
                data-cy="operInst"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.isAcctNbr')}
                id="fim-accounts-wq-isAcctNbr"
                name="isAcctNbr"
                data-cy="isAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.bndAcctNbr')}
                id="fim-accounts-wq-bndAcctNbr"
                name="bndAcctNbr"
                data-cy="bndAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.closingId')}
                id="fim-accounts-wq-closingId"
                name="closingId"
                data-cy="closingId"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.subSegment')}
                id="fim-accounts-wq-subSegment"
                name="subSegment"
                data-cy="subSegment"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.branchCode')}
                id="fim-accounts-wq-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.acctStatus')}
                id="fim-accounts-wq-acctStatus"
                name="acctStatus"
                data-cy="acctStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.ctryCode')}
                id="fim-accounts-wq-ctryCode"
                name="ctryCode"
                data-cy="ctryCode"
                type="text"
                validate={{
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.acctOwners')}
                id="fim-accounts-wq-acctOwners"
                name="acctOwners"
                data-cy="acctOwners"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.remarks')}
                id="fim-accounts-wq-remarks"
                name="remarks"
                data-cy="remarks"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.createdBy')}
                id="fim-accounts-wq-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.createdTs')}
                id="fim-accounts-wq-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.updatedBy')}
                id="fim-accounts-wq-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.updatedTs')}
                id="fim-accounts-wq-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.recordStatus')}
                id="fim-accounts-wq-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimAccountsWq.uploadRemark')}
                id="fim-accounts-wq-uploadRemark"
                name="uploadRemark"
                data-cy="uploadRemark"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-accounts-wq" replace color="info">
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

export default FimAccountsWqUpdate;
