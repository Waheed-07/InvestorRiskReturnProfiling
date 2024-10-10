import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './verifiable-attributes.reducer';

export const VerifiableAttributesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const verifiableAttributesEntity = useAppSelector(state => state.simplerishta.verifiableAttributes.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="verifiableAttributesDetailsHeading">
          <Translate contentKey="simpleRishtaApp.simpleRishtaVerifiableAttributes.detail.title">VerifiableAttributes</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{verifiableAttributesEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="simpleRishtaApp.simpleRishtaVerifiableAttributes.name">Name</Translate>
            </span>
          </dt>
          <dd>{verifiableAttributesEntity.name}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="simpleRishtaApp.simpleRishtaVerifiableAttributes.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{verifiableAttributesEntity.createdBy}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="simpleRishtaApp.simpleRishtaVerifiableAttributes.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {verifiableAttributesEntity.createdAt ? (
              <TextFormat value={verifiableAttributesEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="simpleRishtaApp.simpleRishtaVerifiableAttributes.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {verifiableAttributesEntity.updatedAt ? (
              <TextFormat value={verifiableAttributesEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/simplerishta/verifiable-attributes" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/simplerishta/verifiable-attributes/${verifiableAttributesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default VerifiableAttributesDetail;
