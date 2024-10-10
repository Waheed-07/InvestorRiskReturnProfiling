import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './application-configurations.reducer';

export const ApplicationConfigurationsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const applicationConfigurationsEntity = useAppSelector(state => state.simplerishta.applicationConfigurations.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="applicationConfigurationsDetailsHeading">
          <Translate contentKey="simpleRishtaApp.simpleRishtaApplicationConfigurations.detail.title">ApplicationConfigurations</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{applicationConfigurationsEntity.id}</dd>
          <dt>
            <span id="configKey">
              <Translate contentKey="simpleRishtaApp.simpleRishtaApplicationConfigurations.configKey">Config Key</Translate>
            </span>
          </dt>
          <dd>{applicationConfigurationsEntity.configKey}</dd>
          <dt>
            <span id="configValue">
              <Translate contentKey="simpleRishtaApp.simpleRishtaApplicationConfigurations.configValue">Config Value</Translate>
            </span>
          </dt>
          <dd>{applicationConfigurationsEntity.configValue}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="simpleRishtaApp.simpleRishtaApplicationConfigurations.description">Description</Translate>
            </span>
          </dt>
          <dd>{applicationConfigurationsEntity.description}</dd>
          <dt>
            <span id="countryCode">
              <Translate contentKey="simpleRishtaApp.simpleRishtaApplicationConfigurations.countryCode">Country Code</Translate>
            </span>
          </dt>
          <dd>{applicationConfigurationsEntity.countryCode}</dd>
        </dl>
        <Button tag={Link} to="/simplerishta/application-configurations" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/simplerishta/application-configurations/${applicationConfigurationsEntity.id}/edit`}
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

export default ApplicationConfigurationsDetail;
