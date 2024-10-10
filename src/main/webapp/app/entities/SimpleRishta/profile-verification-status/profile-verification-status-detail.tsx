import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './profile-verification-status.reducer';

export const ProfileVerificationStatusDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const profileVerificationStatusEntity = useAppSelector(state => state.simplerishta.profileVerificationStatus.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="profileVerificationStatusDetailsHeading">
          <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.detail.title">ProfileVerificationStatus</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{profileVerificationStatusEntity.id}</dd>
          <dt>
            <span id="attributeName">
              <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.attributeName">Attribute Name</Translate>
            </span>
          </dt>
          <dd>{profileVerificationStatusEntity.attributeName}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.status">Status</Translate>
            </span>
          </dt>
          <dd>{profileVerificationStatusEntity.status}</dd>
          <dt>
            <span id="verifiedAt">
              <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedAt">Verified At</Translate>
            </span>
          </dt>
          <dd>
            {profileVerificationStatusEntity.verifiedAt ? (
              <TextFormat value={profileVerificationStatusEntity.verifiedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="verifiedBy">
              <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedBy">Verified By</Translate>
            </span>
          </dt>
          <dd>{profileVerificationStatusEntity.verifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/simplerishta/profile-verification-status" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/simplerishta/profile-verification-status/${profileVerificationStatusEntity.id}/edit`}
          replace
          color="primary"
        >
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProfileVerificationStatusDetail;
