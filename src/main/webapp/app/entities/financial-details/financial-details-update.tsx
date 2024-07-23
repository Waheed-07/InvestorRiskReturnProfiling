import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFinancialDetails } from 'app/shared/model/financial-details.model';
import { getEntity, updateEntity, createEntity, reset } from './financial-details.reducer';

export const FinancialDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const financialDetailsEntity = useAppSelector(state => state.financialDetails.entity);
  const loading = useAppSelector(state => state.financialDetails.loading);
  const updating = useAppSelector(state => state.financialDetails.updating);
  const updateSuccess = useAppSelector(state => state.financialDetails.updateSuccess);

  const handleClose = () => {
    navigate('/financial-details');
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
    if (values.annualIncome !== undefined && typeof values.annualIncome !== 'number') {
      values.annualIncome = Number(values.annualIncome);
    }
    if (values.netWorth !== undefined && typeof values.netWorth !== 'number') {
      values.netWorth = Number(values.netWorth);
    }
    if (values.currentSavings !== undefined && typeof values.currentSavings !== 'number') {
      values.currentSavings = Number(values.currentSavings);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...financialDetailsEntity,
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
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...financialDetailsEntity,
          createdAt: convertDateTimeFromServer(financialDetailsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(financialDetailsEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="myApp.financialDetails.home.createOrEditLabel" data-cy="FinancialDetailsCreateUpdateHeading">
            <Translate contentKey="myApp.financialDetails.home.createOrEditLabel">Create or edit a FinancialDetails</Translate>
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
                  id="financial-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('myApp.financialDetails.annualIncome')}
                id="financial-details-annualIncome"
                name="annualIncome"
                data-cy="annualIncome"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('myApp.financialDetails.netWorth')}
                id="financial-details-netWorth"
                name="netWorth"
                data-cy="netWorth"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('myApp.financialDetails.currentSavings')}
                id="financial-details-currentSavings"
                name="currentSavings"
                data-cy="currentSavings"
                type="text"
              />
              <ValidatedField
                label={translate('myApp.financialDetails.investmentExperience')}
                id="financial-details-investmentExperience"
                name="investmentExperience"
                data-cy="investmentExperience"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('myApp.financialDetails.sourceOfFunds')}
                id="financial-details-sourceOfFunds"
                name="sourceOfFunds"
                data-cy="sourceOfFunds"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('myApp.financialDetails.createdAt')}
                id="financial-details-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('myApp.financialDetails.updatedAt')}
                id="financial-details-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/financial-details" replace color="info">
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

export default FinancialDetailsUpdate;
