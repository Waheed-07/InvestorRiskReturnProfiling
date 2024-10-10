import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-verification-attributes.reducer';

export const UserVerificationAttributesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const userVerificationAttributesEntity = useAppSelector(state => state.simplerishta.userVerificationAttributes.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userVerificationAttributesDetailsHeading">
          <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.detail.title">UserVerificationAttributes</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userVerificationAttributesEntity.id}</dd>
          <dt>
            <span id="documentType">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentType">Document Type</Translate>
            </span>
          </dt>
          <dd>{userVerificationAttributesEntity.documentType}</dd>
          <dt>
            <span id="documentUrl">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentUrl">Document Url</Translate>
            </span>
          </dt>
          <dd>{userVerificationAttributesEntity.documentUrl}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.status">Status</Translate>
            </span>
          </dt>
          <dd>{userVerificationAttributesEntity.status}</dd>
          <dt>
            <span id="verificationToken">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.verificationToken">
                Verification Token
              </Translate>
            </span>
          </dt>
          <dd>{userVerificationAttributesEntity.verificationToken}</dd>
          <dt>
            <span id="lastActionDate">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.lastActionDate">Last Action Date</Translate>
            </span>
          </dt>
          <dd>
            {userVerificationAttributesEntity.lastActionDate ? (
              <TextFormat value={userVerificationAttributesEntity.lastActionDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {userVerificationAttributesEntity.createdAt ? (
              <TextFormat value={userVerificationAttributesEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {userVerificationAttributesEntity.updatedAt ? (
              <TextFormat value={userVerificationAttributesEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/simplerishta/user-verification-attributes" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/simplerishta/user-verification-attributes/${userVerificationAttributesEntity.id}/edit`}
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

export default UserVerificationAttributesDetail;
