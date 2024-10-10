import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './user-verification-attributes.reducer';

export const UserVerificationAttributesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const userVerificationAttributesEntity = useAppSelector(state => state.simplerishta.userVerificationAttributes.entity);
  const loading = useAppSelector(state => state.simplerishta.userVerificationAttributes.loading);
  const updating = useAppSelector(state => state.simplerishta.userVerificationAttributes.updating);
  const updateSuccess = useAppSelector(state => state.simplerishta.userVerificationAttributes.updateSuccess);

  const handleClose = () => {
    navigate('/simplerishta/user-verification-attributes');
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

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    values.lastActionDate = convertDateTimeToServer(values.lastActionDate);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...userVerificationAttributesEntity,
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
          lastActionDate: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...userVerificationAttributesEntity,
          lastActionDate: convertDateTimeFromServer(userVerificationAttributesEntity.lastActionDate),
          createdAt: convertDateTimeFromServer(userVerificationAttributesEntity.createdAt),
          updatedAt: convertDateTimeFromServer(userVerificationAttributesEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.createOrEditLabel"
            data-cy="UserVerificationAttributesCreateUpdateHeading"
          >
            <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.createOrEditLabel">
              Create or edit a UserVerificationAttributes
            </Translate>
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
                  id="user-verification-attributes-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentType')}
                id="user-verification-attributes-documentType"
                name="documentType"
                data-cy="documentType"
                type="text"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentUrl')}
                id="user-verification-attributes-documentUrl"
                name="documentUrl"
                data-cy="documentUrl"
                type="text"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.status')}
                id="user-verification-attributes-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.verificationToken')}
                id="user-verification-attributes-verificationToken"
                name="verificationToken"
                data-cy="verificationToken"
                type="text"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.lastActionDate')}
                id="user-verification-attributes-lastActionDate"
                name="lastActionDate"
                data-cy="lastActionDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.createdAt')}
                id="user-verification-attributes-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaUserVerificationAttributes.updatedAt')}
                id="user-verification-attributes-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/simplerishta/user-verification-attributes"
                replace
                color="info"
              >
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

export default UserVerificationAttributesUpdate;
