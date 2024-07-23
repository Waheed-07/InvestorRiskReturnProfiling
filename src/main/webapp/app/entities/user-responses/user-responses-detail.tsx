import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-responses.reducer';

export const UserResponsesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const userResponsesEntity = useAppSelector(state => state.userResponses.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userResponsesDetailsHeading">
          <Translate contentKey="myApp.userResponses.detail.title">UserResponses</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userResponsesEntity.id}</dd>
          <dt>
            <span id="responseText">
              <Translate contentKey="myApp.userResponses.responseText">Response Text</Translate>
            </span>
          </dt>
          <dd>{userResponsesEntity.responseText}</dd>
          <dt>
            <span id="responseDate">
              <Translate contentKey="myApp.userResponses.responseDate">Response Date</Translate>
            </span>
          </dt>
          <dd>
            {userResponsesEntity.responseDate ? (
              <TextFormat value={userResponsesEntity.responseDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/user-responses" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-responses/${userResponsesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserResponsesDetail;
