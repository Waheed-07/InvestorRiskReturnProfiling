import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimSettAcctWq } from 'app/shared/model/FIM/fim-sett-acct-wq.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-sett-acct-wq.reducer';

export const FimSettAcctWqUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimSettAcctWqEntity = useAppSelector(state => state.fim.fimSettAcctWq.entity);
  const loading = useAppSelector(state => state.fim.fimSettAcctWq.loading);
  const updating = useAppSelector(state => state.fim.fimSettAcctWq.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimSettAcctWq.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-sett-acct-wq');
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
      ...fimSettAcctWqEntity,
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
          ...fimSettAcctWqEntity,
          createdTs: convertDateTimeFromServer(fimSettAcctWqEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimSettAcctWqEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimSettAcctWq.home.createOrEditLabel" data-cy="FimSettAcctWqCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimSettAcctWq.home.createOrEditLabel">Create or edit a FimSettAcctWq</Translate>
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
                  id="fim-sett-acct-wq-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.settaccId')}
                id="fim-sett-acct-wq-settaccId"
                name="settaccId"
                data-cy="settaccId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.accountId')}
                id="fim-sett-acct-wq-accountId"
                name="accountId"
                data-cy="accountId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 15, message: translate('entity.validation.maxlength', { max: 15 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.settAcctNbr')}
                id="fim-sett-acct-wq-settAcctNbr"
                name="settAcctNbr"
                data-cy="settAcctNbr"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.settCcy')}
                id="fim-sett-acct-wq-settCcy"
                name="settCcy"
                data-cy="settCcy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.settAcctStatus')}
                id="fim-sett-acct-wq-settAcctStatus"
                name="settAcctStatus"
                data-cy="settAcctStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.createdBy')}
                id="fim-sett-acct-wq-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.createdTs')}
                id="fim-sett-acct-wq-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.updatedBy')}
                id="fim-sett-acct-wq-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.updatedTs')}
                id="fim-sett-acct-wq-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.recordStatus')}
                id="fim-sett-acct-wq-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
                validate={{
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimSettAcctWq.uploadRemark')}
                id="fim-sett-acct-wq-uploadRemark"
                name="uploadRemark"
                data-cy="uploadRemark"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-sett-acct-wq" replace color="info">
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

export default FimSettAcctWqUpdate;
