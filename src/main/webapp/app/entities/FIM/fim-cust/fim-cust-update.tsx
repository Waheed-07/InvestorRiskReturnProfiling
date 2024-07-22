import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFimCust } from 'app/shared/model/FIM/fim-cust.model';
import { getEntity, updateEntity, createEntity, reset } from './fim-cust.reducer';

export const FimCustUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const fimCustEntity = useAppSelector(state => state.fim.fimCust.entity);
  const loading = useAppSelector(state => state.fim.fimCust.loading);
  const updating = useAppSelector(state => state.fim.fimCust.updating);
  const updateSuccess = useAppSelector(state => state.fim.fimCust.updateSuccess);

  const handleClose = () => {
    navigate('/fim/fim-cust' + location.search);
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
      ...fimCustEntity,
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
          ...fimCustEntity,
          createdTs: convertDateTimeFromServer(fimCustEntity.createdTs),
          updatedTs: convertDateTimeFromServer(fimCustEntity.updatedTs),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="fimApp.fimFimCust.home.createOrEditLabel" data-cy="FimCustCreateUpdateHeading">
            <Translate contentKey="fimApp.fimFimCust.home.createOrEditLabel">Create or edit a FimCust</Translate>
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
                  id="fim-cust-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('fimApp.fimFimCust.custId')}
                id="fim-cust-custId"
                name="custId"
                data-cy="custId"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.clientId')}
                id="fim-cust-clientId"
                name="clientId"
                data-cy="clientId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 30, message: translate('entity.validation.maxlength', { max: 30 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.idType')}
                id="fim-cust-idType"
                name="idType"
                data-cy="idType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 10, message: translate('entity.validation.maxlength', { max: 10 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.ctryCode')}
                id="fim-cust-ctryCode"
                name="ctryCode"
                data-cy="ctryCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 3, message: translate('entity.validation.maxlength', { max: 3 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.createdBy')}
                id="fim-cust-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.createdTs')}
                id="fim-cust-createdTs"
                name="createdTs"
                data-cy="createdTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.updatedBy')}
                id="fim-cust-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
                validate={{
                  maxLength: { value: 8, message: translate('entity.validation.maxlength', { max: 8 }) },
                }}
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.updatedTs')}
                id="fim-cust-updatedTs"
                name="updatedTs"
                data-cy="updatedTs"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.recordStatus')}
                id="fim-cust-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="text"
              />
              <ValidatedField
                label={translate('fimApp.fimFimCust.uploadRemark')}
                id="fim-cust-uploadRemark"
                name="uploadRemark"
                data-cy="uploadRemark"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/fim/fim-cust" replace color="info">
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

export default FimCustUpdate;
