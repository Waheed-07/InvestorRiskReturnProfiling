import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './profession.reducer';

export const ProfessionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const professionEntity = useAppSelector(state => state.profession.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="professionDetailsHeading">
          <Translate contentKey="myApp.profession.detail.title">Profession</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{professionEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="myApp.profession.name">Name</Translate>
            </span>
          </dt>
          <dd>{professionEntity.name}</dd>
          <dt>
            <span id="conceptUri">
              <Translate contentKey="myApp.profession.conceptUri">Concept Uri</Translate>
            </span>
          </dt>
          <dd>{professionEntity.conceptUri}</dd>
          <dt>
            <span id="iscoGroup">
              <Translate contentKey="myApp.profession.iscoGroup">Isco Group</Translate>
            </span>
          </dt>
          <dd>{professionEntity.iscoGroup}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="myApp.profession.description">Description</Translate>
            </span>
          </dt>
          <dd>{professionEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/profession" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/profession/${professionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProfessionDetail;
