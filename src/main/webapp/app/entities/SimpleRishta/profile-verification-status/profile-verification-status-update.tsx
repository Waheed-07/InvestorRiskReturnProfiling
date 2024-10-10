import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './profile-verification-status.reducer';

export const ProfileVerificationStatusUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const profileVerificationStatusEntity = useAppSelector(state => state.simplerishta.profileVerificationStatus.entity);
  const loading = useAppSelector(state => state.simplerishta.profileVerificationStatus.loading);
  const updating = useAppSelector(state => state.simplerishta.profileVerificationStatus.updating);
  const updateSuccess = useAppSelector(state => state.simplerishta.profileVerificationStatus.updateSuccess);

  const handleClose = () => {
    navigate('/simplerishta/profile-verification-status');
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
    values.verifiedAt = convertDateTimeToServer(values.verifiedAt);

    const entity = {
      ...profileVerificationStatusEntity,
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
          verifiedAt: displayDefaultDateTime(),
        }
      : {
          ...profileVerificationStatusEntity,
          verifiedAt: convertDateTimeFromServer(profileVerificationStatusEntity.verifiedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.createOrEditLabel"
            data-cy="ProfileVerificationStatusCreateUpdateHeading"
          >
            <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.createOrEditLabel">
              Create or edit a ProfileVerificationStatus
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
                  id="profile-verification-status-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaProfileVerificationStatus.attributeName')}
                id="profile-verification-status-attributeName"
                name="attributeName"
                data-cy="attributeName"
                type="text"
                validate={{
                  maxLength: { value: 200, message: translate('entity.validation.maxlength', { max: 200 }) },
                }}
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaProfileVerificationStatus.status')}
                id="profile-verification-status-status"
                name="status"
                data-cy="status"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedAt')}
                id="profile-verification-status-verifiedAt"
                name="verifiedAt"
                data-cy="verifiedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedBy')}
                id="profile-verification-status-verifiedBy"
                name="verifiedBy"
                data-cy="verifiedBy"
                type="text"
              />
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/simplerishta/profile-verification-status"
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

export default ProfileVerificationStatusUpdate;
